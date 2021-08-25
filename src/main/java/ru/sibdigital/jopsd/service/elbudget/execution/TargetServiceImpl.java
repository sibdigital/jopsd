package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.TargetMatch;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.Target;
import ru.sibdigital.jopsd.model.TargetExecutionValue;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.model.WorkPackageTarget;
import ru.sibdigital.jopsd.model.enums.TargetTypes;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Service
@Slf4j
public class TargetServiceImpl extends SuperServiceImpl implements TargetService {

    @Override
    public List<TargetMatch> matchTargets(InputStream inputStream) throws Exception {
        Resultsexecution resultsexecution = executionParseService.unmarshalInputStream(inputStream);
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> purposeCriteriaList = executionParseService.getPurposeCriteriaList(resultsexecution.getRegProject());
        List<TargetMatch> targetMatches = new ArrayList<>();
        purposeCriteriaList.forEach(purposeCriteria -> {
            Target target = findTarget(purposeCriteria);
            targetMatches.add(TargetMatch.builder().target(target).purposeCriteria(purposeCriteria).build());
        });

        return targetMatches;
    }

    @Override
    public List<TargetMatch> createAndSaveTargetValues(List<TargetMatch> targetMatches, Map<String, Object> params) {
        List<WorkPackageTarget> workPackageTargetList = new ArrayList<>();
        List<TargetMatch> targetMatchesAfterProcess = new ArrayList<>();
        Target target;

        Long workPackageId = (Long) params.get("workPackageId");
        WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);

        for (TargetMatch targetMatch : targetMatches) {
            if (targetMatch.getCreateNewTarget()) {
                target = createTarget(targetMatch, workPackage);
                target.setComment("Загружено из Эл. Бюджета");
            } else {
                // если с frontend не все поля пришли, чтобы не перезатереть:
                target = targetRepo.findById(targetMatch.getTarget().getId()).orElse(null);
                target.setMetaId(targetMatch.getPurposeCriteria().getPurposeCriteriaMetaId());
            }
            targetRepo.save(target);

            Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria purposeCriteria = targetMatch.getPurposeCriteria();
            List<WorkPackageTarget> workPackageTargets = parsePurposeCriteria(purposeCriteria, target, workPackage);

            workPackageTargetList.addAll(workPackageTargets);
            targetMatchesAfterProcess.add(TargetMatch.builder().target(target).purposeCriteria(purposeCriteria).createNewTarget(false).build());
        }

        workPackageTargetRepo.saveAll(workPackageTargetList);

        return targetMatchesAfterProcess;
    }

    private List<WorkPackageTarget> parsePurposeCriteria(Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria criteria, Target target, WorkPackage workPackage) {
        List<WorkPackageTarget> workPackageTargets = new ArrayList<>();

        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        Map<Integer, WorkPackageTarget> workPackageTargetMap = getWorkPackageTargetsByMonths(workPackage, target, year);
        Map<Integer, TargetExecutionValue> targetExecutionValueMap = getTargetExecutionValuesByQuarters(target, year);

        Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions monthlyExecutions = criteria.getPurposeCriteriaMonthlyExecutions();
        if (monthlyExecutions != null) {
            List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution> monthlyExecutionList =
                    monthlyExecutions.getPurposeCriteriaMonthlyExecution();

            for (Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution monthlyExecution : monthlyExecutionList) {
                Integer month =  getMonth(monthlyExecution);
                Integer quarter = getQuarterByMonth(month);

                WorkPackageTarget workPackageTarget = workPackageTargetMap.get(month);
                if (workPackageTarget != null) {
                    workPackageTarget.setValue(monthlyExecution.getFactPrognos());
                    workPackageTarget.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                } else {
                    TargetExecutionValue targetExecutionValue = targetExecutionValueMap.get(quarter);
                    BigDecimal planValue = (targetExecutionValue != null) ? targetExecutionValue.getValue() : null;

                    workPackageTarget= WorkPackageTarget.builder()
                            .projectId(workPackage.getProjectId())
                            .workPackageId(workPackage.getId())
                            .targetId(target.getId())
                            .year(year) // TODO изменить на Integer в master
                            .quarter(quarter) // TODO изменить на Integer в master
                            .month(month) // TODO изменить на Integer в master
                            .value(monthlyExecution.getFactPrognos()) // TODO В master изменить тип value на BigDecimal
                            .createdAt(new Timestamp(System.currentTimeMillis()))
                            .updatedAt(new Timestamp(System.currentTimeMillis()))
                            .planValue(planValue)
                            .build();
                }

                workPackageTargets.add(workPackageTarget);
            }
        }

        return workPackageTargets;
    }

    private Map<Integer, WorkPackageTarget> getWorkPackageTargetsByMonths(WorkPackage workPackage, Target target, Integer year) {
        Map<Integer, WorkPackageTarget> map = new HashMap<>();
        List<WorkPackageTarget> workPackageTargets = workPackageTargetRepo.findAllByWorkPackageIdAndTargetIdAndYear(workPackage.getId(), target.getId(), year);
        workPackageTargets.forEach(workPackageTarget -> map.put(workPackageTarget.getMonth(), workPackageTarget));

        return map;
    }

    private Map<Integer, TargetExecutionValue> getTargetExecutionValuesByQuarters(Target target, Integer year) {
        Map<Integer, TargetExecutionValue> map = new HashMap<>();
        List<TargetExecutionValue> targetExecutionValues = targetExecutionValueRepo.findAllByTargetIdAndYear(target.getId(), year);
        targetExecutionValues.forEach(targetExecutionValue -> {map.put(targetExecutionValue.getQuarter(), targetExecutionValue);});

        return map;
    }

    private Integer getMonth(Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution monthlyExecution) {
        String str = monthlyExecution.getMonth();
        Integer monthNumber = Integer.parseInt(str) + 1;
        return monthNumber;
    }

    private Target findTarget(Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria purposeCriteria) {
        return targetRepo.findTargetByMetaId(purposeCriteria.getPurposeCriteriaMetaId()).orElse(null);
    }

    private Target createTarget(TargetMatch targetMatch, WorkPackage workPackage) {
        return Target.builder()
                    .name(targetMatch.getNewTargetName())
                    .typeId(TargetTypes.PURPOSE.getValue())
                    .parentId(Long.valueOf(0))
                    .projectId(workPackage.getProjectId())
                    .createdAt(Timestamp.from(Instant.now()))
                    .updatedAt(Timestamp.from(Instant.now()))
                    .metaId(targetMatch.getPurposeCriteria().getPurposeCriteriaMetaId())
                    .build();
    }
}
