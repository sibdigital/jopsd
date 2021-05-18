package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.model.WorkPackageTarget;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TargetServiceImpl extends SuperServiceImpl implements TargetService {
    @Override
    public void saveTargets(Resultsexecution.RegProject regProject, Map<String, Object> params) {
        List<WorkPackageTarget> targetList = new ArrayList<>();
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> criteriaList = getPurposeCriteriaList(regProject);
        if (criteriaList != null) {
            for (Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria criteria : criteriaList) {
                List<WorkPackageTarget> targets = parsePurposeCriteria(criteria, params);
                targetList.addAll(targets);
            }

//        workPackageTargetRepo.saveAll(targetList);
        }
    }

    private  List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> getPurposeCriteriaList(Resultsexecution.RegProject regProject) {
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> purposeCriteriaList = null;
        Resultsexecution.RegProject.PurposeCriterias criterias = regProject.getPurposeCriterias();
        if (criterias != null) {
            purposeCriteriaList = criterias.getPurposeCriteria();
        }

        return purposeCriteriaList;
    }

    private List<WorkPackageTarget> parsePurposeCriteria(Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria criteria, Map<String, Object> params) {
        List<WorkPackageTarget> targets = null;
        Long workPackageId = (Long) params.get("workPackageId");
        WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);
        Long projectId = (workPackage == null) ? null : workPackage.getProjectId();

        Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions monthlyExecutions = criteria.getPurposeCriteriaMonthlyExecutions();
        if (monthlyExecutions != null) {
            List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution> monthlyExecutionList =
                monthlyExecutions.getPurposeCriteriaMonthlyExecution();

            for (Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution monthlyExecution : monthlyExecutionList) {
                WorkPackageTarget workPackageTarget= WorkPackageTarget.builder()
                        .projectId(projectId)
                        .workPackageId(workPackageId)
//                        .targetId(null)
//                        .year()
//                        .quarter()
                        .month(Long.parseLong(monthlyExecution.getMonth()))
//                        .value()
//                        .type()
                        .createdAt(new Timestamp(System.currentTimeMillis()))
                        .updatedAt(new Timestamp(System.currentTimeMillis()))
//                        .planValue()
                      .build();
                targets.add(workPackageTarget);
            }
        }

        return targets;
    }
}
