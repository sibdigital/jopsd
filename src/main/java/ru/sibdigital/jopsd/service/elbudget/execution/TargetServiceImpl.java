package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.TargetMatch;
import ru.sibdigital.jopsd.dto.elbudget.execution.EbRisk;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.enums.TargetTypes;
import ru.sibdigital.jopsd.model.opsd.*;
import ru.sibdigital.jopsd.service.SuperServiceImpl;
import ru.sibdigital.jopsd.utils.DateTimeUtils;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

            // если с frontend не все поля пришли, чтобы не перезатереть:
            if (targetMatch.getTarget() != null) {
                target = targetRepo.findById(targetMatch.getTarget().getId()).orElse(null);
                target.setMetaId(targetMatch.getPurposeCriteria().getPurposeCriteriaMetaId());
                targetRepo.save(target);
            } else {
                target = null;
            }


            if (target != null) {
                Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria purposeCriteria = targetMatch.getPurposeCriteria();
                List<WorkPackageTarget> workPackageTargets = parsePurposeCriteria(purposeCriteria, target, workPackage);

                workPackageTargetList.addAll(workPackageTargets);
                targetMatchesAfterProcess.add(TargetMatch.builder().target(target).purposeCriteria(purposeCriteria).build());

                List<EbRisk> risks =
                        purposeCriteria.getRisks().stream()
                                .map(item -> item.getRisk())
                                .filter(item -> item != null)
                                .collect(Collectors.toList());
                riskService.saveEbRisksForTargets(risks, target);
            }
        }

        workPackageTargetRepo.saveAll(workPackageTargetList);

        return targetMatchesAfterProcess;
    }

    private List<WorkPackageTarget> parsePurposeCriteria(Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria criteria, Target target, WorkPackage workPackage) {
        List<WorkPackageTarget> workPackageTargets = new ArrayList<>();

        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        Map<Integer, WorkPackageTarget> workPackageTargetMap = getWorkPackageTargetsByMonths(workPackage, target, year);
        Map<Integer, TargetExecutionValue> targetExecutionValueMap = getTargetExecutionValuesByQuarters(target, year);
        LocalDate now = LocalDate.now();
        BigDecimal varValue = null;
        BigDecimal varPlanValue = null;

        Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions monthlyExecutions = criteria.getPurposeCriteriaMonthlyExecutions();
        if (monthlyExecutions != null) {
            List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution> monthlyExecutionList =
                    monthlyExecutions.getPurposeCriteriaMonthlyExecution();

            for (Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution monthlyExecution : monthlyExecutionList) {
                Integer month =  getMonth(monthlyExecution);
                Integer quarter = DateTimeUtils.getQuarterByMonth(month);

                WorkPackageTarget workPackageTarget = workPackageTargetMap.get(month);
                if (workPackageTarget != null) {
                    if(((month > now.getMonthValue()) && (year == now.getYear())) || year > now.getYear()){
                        workPackageTarget.setPlanValue(monthlyExecution.getFactPrognos());
//                        workPackageTarget.setValue(null); //should be null?
                    }
                    else {
                        workPackageTarget.setValue(monthlyExecution.getFactPrognos());
//                        workPackageTarget.setPlanValue(null); //should be null?
                    }

                    workPackageTarget.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                } else {
                    TargetExecutionValue targetExecutionValue = targetExecutionValueMap.get(quarter);
                    BigDecimal planValue = (targetExecutionValue != null) ? targetExecutionValue.getValue() : null;


                    if(((month > now.getMonthValue()) && (year == now.getYear())) || year > now.getYear()){
                        varValue=null;
                        varPlanValue=monthlyExecution.getFactPrognos();
                    } else {
                        varValue=monthlyExecution.getFactPrognos();
                        varPlanValue=null;
                    }

                    workPackageTarget= WorkPackageTarget.builder()
                            .project(workPackage.getProject())
                            .workPackage(workPackage)
                            .target(target)
                            .year(year)
                            .quarter(quarter)
                            .month(month)
                            .value(varValue)
                            .createdAt(new Timestamp(System.currentTimeMillis()))
                            .updatedAt(new Timestamp(System.currentTimeMillis()))
                            .planValue(varPlanValue)
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

    @Override
    public Target changeMetaId(Long targetId, Long metaId) {
        Target target = targetRepo.findById(targetId).orElse(null);
        target.setMetaId(null);
        targetRepo.save(target);
        return target;
    }

    @Override
    public Target saveRpResultIndicator(Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator rpResultIndicator, WorkPackage workPackage) {
        if (rpResultIndicator != null) {
            Target target = targetRepo.findTargetByMetaId(rpResultIndicator.getResultIndicatorMetaId()).orElse(new Target());
            if (target.getName() == null) {
                target.setName(workPackage.getSubject());
            }
            target.setProject(workPackage.getProject());
            target.setParentId(0L);
            target.setMetaId(rpResultIndicator.getResultIndicatorMetaId());
            target.setTargetType(enumerationRepo.findById(TargetTypes.RESULT.getValue()).orElse(null));
            if (rpResultIndicator.getValueFact() != null) {
                target.setBasicValue(rpResultIndicator.getValueFact().doubleValue());
            }
            if (rpResultIndicator.getValuePrognosInEndOfYear() != null) {
                target.setPlanValue(rpResultIndicator.getValuePrognosInEndOfYear().doubleValue());
            }

            if (rpResultIndicator.getExecutionDate() != null) {
                XMLGregorianCalendar xgc = rpResultIndicator.getExecutionDate();
                GregorianCalendar gc = xgc.toGregorianCalendar();
                target.setResultDueDate(gc.toZonedDateTime().toLocalDateTime());
            }

            if (target.getCreatedAt() == null) {
                target.setCreatedAt(Timestamp.from(Instant.now()));
            }
            target.setUpdatedAt(Timestamp.from(Instant.now()));

            targetRepo.save(target);

            WorkPackageTarget workPackageTarget = workPackageTargetRepo.findWorkPackageTargetAsRpResultIndicator(workPackage.getId(), Calendar.getInstance().get(Calendar.YEAR));
            if (workPackageTarget == null) {
                workPackageTarget = WorkPackageTarget.builder()
                        .workPackage(workPackage)
                        .target(target)
                        .project(workPackage.getProject())
                        .value((target.getBasicValue() != null) ? BigDecimal.valueOf(target.getBasicValue()) : null)
                        .planValue((target.getPlanValue() != null) ? BigDecimal.valueOf(target.getPlanValue()) : null)
                        .month(12)
                        .quarter(4)
                        .year(Calendar.getInstance().get(Calendar.YEAR))
                        .createdAt(Timestamp.from(Instant.now()))
                        .updatedAt(Timestamp.from(Instant.now()))
                        .build();
            } else {
                workPackageTarget.setPlanValue((target.getPlanValue() != null) ? BigDecimal.valueOf(target.getPlanValue()) : null);
                workPackageTarget.setValue((target.getBasicValue() != null) ? BigDecimal.valueOf(target.getBasicValue()) : null);
            }

            workPackageTargetRepo.save(workPackageTarget);

            List<EbRisk> riskList =
                    rpResultIndicator.getRisks().stream().map(item -> item.getRisk()).collect(Collectors.toList());
            riskService.saveEbRisksForTargets(riskList, target);

            return target;
        }
        else {
            return null;
        }
    }

    @Override
    public List<Target> getTargetsByWorkPackage(WorkPackage workPackage) {
        return workPackageTargetRepo.findAllByWorkPackage(workPackage).stream()
                .filter(wpp -> wpp.getTarget() != null && wpp.getTarget().getTargetType().getId() != Long.valueOf(41))
                .map(wpp -> wpp.getTarget())
                .distinct()
                .collect(Collectors.toList());
    }
}
