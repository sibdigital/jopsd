package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.model.WorkPackageTarget;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TargetServiceImpl extends SuperServiceImpl implements TargetService {
    @Override
    public void saveTargets(Resultsexecution.RegProject regProject, WorkPackage workPackage, Map<String, Object> params) {
        List<WorkPackageTarget> targetList = new ArrayList<>();
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> criteriaList =  executionParseService.getPurposeCriteriaList(regProject);
        if (criteriaList != null) {
            for (Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria criteria : criteriaList) {
                List<WorkPackageTarget> targets = parsePurposeCriteria(criteria, workPackage, params);
                targetList.addAll(targets);
            }

//        workPackageTargetRepo.saveAll(targetList);
        }
    }

    private List<WorkPackageTarget> parsePurposeCriteria(Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria criteria, WorkPackage workPackage, Map<String, Object> params) {
        List<WorkPackageTarget> targets = null;

        Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions monthlyExecutions = criteria.getPurposeCriteriaMonthlyExecutions();
        if (monthlyExecutions != null) {
            List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution> monthlyExecutionList =
                monthlyExecutions.getPurposeCriteriaMonthlyExecution();

            for (Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution monthlyExecution : monthlyExecutionList) {
                Integer month =  getMonth(monthlyExecution);
                WorkPackageTarget workPackageTarget= WorkPackageTarget.builder()
                        .projectId(workPackage.getProjectId())
                        .workPackageId(workPackage.getId())
//                        .targetId(null) // TODO вставить значение сопоставленного target'а
                        .year(Long.valueOf(Calendar.getInstance().get(Calendar.YEAR)))
                        .quarter(Long.valueOf(getQuarterByMonth(month)))
                        .month(Long.valueOf(month))
                        .value(monthlyExecution.getFactPrognos()) // TODO В master изменить тип value на BigDecimal
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .updatedAt(new Timestamp(System.currentTimeMillis()))
//                        .planValue() // TODO подтянуть из TargetExecutionValue
                      .build();
                targets.add(workPackageTarget);
            }
        }

        return targets;
    }

    private Integer getMonth(Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution monthlyExecution) {
        String str = monthlyExecution.getMonth();
        Integer monthNumber = Integer.parseInt(str) + 1;
        return monthNumber;
    }
}
