package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.CostEntry;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.model.enums.CostTypes;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CostEntryServiceImpl extends SuperServiceImpl implements CostEntryService {
    @Override
    public void saveCostEntries(Resultsexecution.RegProject regProject, Map<String, Object> params) {
        List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceList = getFinancialSourceList(regProject);
        if (financialSourceList != null) {

            // Могут быть строки с одинаковым getRegProjFinValue;
//            Map<String, Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceMap = financialSourceList.stream()
//                    .collect(Collectors.toMap(Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource::getRegProjFinValue, ctr -> ctr));
//            List<CostEntry> costEntryList = parseFinancialSources(financialSourceMap, params);
            ArrayList<String> codes = new ArrayList<>(Arrays.asList("220", "221", "250", "230"));
            List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSources =
                            financialSourceList.stream()
                            .filter(ctr -> codes.contains(ctr.getRegProjFinValue()))
                            .collect(Collectors.toList());


//        costEntryRepo.saveAll(costEntryList);
        }
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

    private List<CostEntry> parseFinancialSources(Map<String, Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceMap, Map<String, Object> params) {
        List<CostEntry> costEntries = null;

        // 220 - бюджет субъекта
        // 221 - федеральный бюджет
        // 250 - внебюджетные источники
        // 230 - свод бюджетов муниципальных образований
        Map<String, CostTypes> mapCode = new HashMap<>();
        mapCode.put("220", CostTypes.REGIONAL_BUDGET);
        mapCode.put("221", CostTypes.FEDERAL_BUDGET);
        mapCode.put("250", CostTypes.EXTRABUDGETARY_FUNDS);
        mapCode.put("230", CostTypes.MUNICIPAL_BUDGET);

        for (Map.Entry<String, CostTypes> item : mapCode.entrySet()) {
            String elBudgetCode = item.getKey();
            CostTypes costType = item.getValue();
            Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource = financialSourceMap.get(elBudgetCode);
            if (financialSource != null) {
                costEntries.add(createCostEntry(financialSource, costType, params));
            }
        }
        return costEntries;
    }



    private CostEntry createCostEntry(Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource,
                                      CostTypes costType, Map<String, Object> params) {

        Long authorId = (Long) params.get("authorId");
        Long workPackageId = (Long) params.get("workPackageId");
        WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);
        Long projectId = (workPackage == null) ? null : workPackage.getProjectId();
        CostEntry costEntry = CostEntry.builder()
                .userId(authorId)
                .projectId(projectId)
                .workPackageId(workPackageId)
                .costTypeId(costType.getValue())
                .units(financialSource.getCBR().doubleValue())
                .costs(financialSource.getCBR())
//                    .spentOn()
                .createdOn(new Timestamp(System.currentTimeMillis()))
                .updatedOn(new Timestamp(System.currentTimeMillis()))
                .comments(financialSource.getComment())
                .blocked(false)
//                    .tyear()
//                    .tmonth()
//                    .tweek()
                .build();

        return costEntry;
    }
}
