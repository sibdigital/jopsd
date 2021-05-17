package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.CostObject;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CostEntryServiceImpl extends SuperServiceImpl implements CostEntryService {
    @Override
    public void saveCostObjects(Resultsexecution.RegProject regProject, Map<String, Object> params) {
        List<CostObject> costObjectList = new ArrayList<>();

        List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceList = getFinancialSourceList(regProject);
        Map<String, Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceMap = financialSourceList.stream()
                                                                            .collect(Collectors.toMap(Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource::getRegProjFinValue, ctr -> ctr));
//        for (Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource : financialSourceList) {
//            List<CostObject> targets = parseFinancialSource(financialSource, params);
//            costObjectList.addAll(targets);
//        }


        costObjectRepo.saveAll(costObjectList);
    }

    private  List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> getFinancialSourceList(Resultsexecution.RegProject regProject) {
        List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceList = null;
        Resultsexecution.RegProject.Results results = regProject.getResults();
        if (results != null) {
            List<Resultsexecution.RegProject.Results.Result> resultList = results.getResult();
            if (resultList != null && !resultList.isEmpty()) {
                Resultsexecution.RegProject.Results.Result result = resultList.get(0);
                Resultsexecution.RegProject.Results.Result.FinancialSources financialSources = result.getFinancialSources();
                if (financialSources != null) {
                    financialSourceList = financialSources.getFinancialSource();
                }
            }
        }

        return financialSourceList;
    }

    private List<CostObject> parseFinancialSources(Map<String, Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceMap, Map<String, Object> params) {
        List<CostObject> costObjects = null;

        Long authorId = (Long) params.get("authorId");
        Long workPackageId = (Long) params.get("workPackageId");
        WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);
        Long projectId = (workPackage == null) ? null : workPackage.getProjectId();

//        for (Map.Entry<String, Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> entry : financialSourceMap.entrySet()) {
            // 220 - бюджет субъекта
            // 221 - федеральный бюджет
            // 250 - внебюджетные источники
            // 230 - свод бюджетов муниципальных образований

        Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource = financialSourceMap.get("220");
        CostObject costObject1 = CostObject.builder()
                                .projectId(projectId)
                                .authorId(authorId)
                                .subject(workPackage.getSubject())
                                .description(financialSource.getComment())
                                .type("VariableCostObject")
//                                .fixedDate()
//                                .createdAt(new Timestamp(System.currentTimeMillis()))
//                                .updatedAt(new Timestamp(System.currentTimeMillis()))
                                .build();
//        }

//
//        Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions monthlyExecutions = criteria.getPurposeCriteriaMonthlyExecutions();
//        if (monthlyExecutions != null) {
//            List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution> monthlyExecutionList =
//                monthlyExecutions.getPurposeCriteriaMonthlyExecution();
//
//            for (Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution monthlyExecution : monthlyExecutionList) {
//                WorkPackageTarget workPackageTarget= WorkPackageTarget.builder()
//                        .projectId(projectId)
//                        .workPackageId(workPackageId)
////                        .targetId(null)
////                        .year()
////                        .quarter()
//                        .month(Long.parseLong(monthlyExecution.getMonth()))
////                        .value()
////                        .type()
//                        .createdAt(new Timestamp(System.currentTimeMillis()))
//                        .updatedAt(new Timestamp(System.currentTimeMillis()))
////                        .planValue()
//                      .build();
//                targets.add(workPackageTarget);
//            }
//        }

//        return targets;
        return null;
    }
}
