package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.*;
import ru.sibdigital.jopsd.model.enums.CostTypes;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinancialServiceImpl extends SuperServiceImpl implements FinancialService {

    public void saveFinances(Resultsexecution.RegProject regProject, WorkPackage workPackage, Map<String, Object> params) {
        CostObject costObject = getCostObjectByWorkPackage(workPackage, params);
        costObject = null; // TODO Бюджет может быть создан и исполнен дочерними КТ. Выше поиск некорректный


        List<MaterialBudgetItem> materialBudgetItemList = new ArrayList<>();
        List<CostEntry> costEntryList = new ArrayList<>();

        List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceList = executionParseService.getFinancialSourceList(regProject);
        if (financialSourceList != null) {
            Map<String, CostTypes> mapCostTypes = getMapCostTypes();
            List<String> codes = new ArrayList<>(mapCostTypes.keySet());
            for (String code : codes) {
                CostTypes costType = mapCostTypes.get(code);

                List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSources =
                        getFinancialSourcesByCode(financialSourceList, code);

                List<MaterialBudgetItem> materialBudgetItems = parseMaterialBudgetItems(financialSources, costType, costObject);
                materialBudgetItemList.addAll(materialBudgetItems);

                List<CostEntry> costEntries = parseCostEntries(financialSources, workPackage, costType, costObject, params);
                costEntryList.addAll(costEntries);
            }
        }

        if (!materialBudgetItemList.isEmpty()) {
            materialBudgetItemRepo.saveAll(materialBudgetItemList);
        }

        if (!costEntryList.isEmpty()) {
            costEntryRepo.saveAll(costEntryList);
        }
    }

    private List<MaterialBudgetItem> parseMaterialBudgetItems(List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSources,
                                          CostTypes costType, CostObject costObject) {
        List<MaterialBudgetItem> newMaterialBudgetItems = new ArrayList<>();

        BigDecimal financialSourcesCBRSum = getFinancialSourceCBRSum(financialSources);

        List<MaterialBudgetItem> budgetItemsInDB = findMaterialBudgetItemsByCostObjectAndCostType(costObject, costType);
        BigDecimal budgetItemsInDBConsolidateUnitsSum = getMaterialBudgetItemConsolidateUnitsSum(budgetItemsInDB);

        if (financialSourcesCBRSum.compareTo(budgetItemsInDBConsolidateUnitsSum) != 0) {
            if (budgetItemsInDBConsolidateUnitsSum.compareTo(BigDecimal.ZERO) == 0) {
                for (Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource : financialSources) {
                    newMaterialBudgetItems.add(createMaterialBudgetItem(financialSource, costType, costObject));
                }
            }

//            else {
//                // TODO Если в ИСУПе уже введены суммы бюджета, и они не равны суммам из Эл.Бюджета
//            }
        }

        return newMaterialBudgetItems;
    }

    private List<CostEntry> parseCostEntries(List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSources,
                                             WorkPackage workPackage, CostTypes costType, CostObject costObject, Map<String, Object> params) {
        List<CostEntry> newCostEntries = new ArrayList<>();

        BigDecimal financialCashExecution = getFinancialSourceCashExecutionSum(financialSources);

        List<CostEntry> costEntriesInDB = findCostEntriesByCostObjectAndCostTypes(costObject, costType);
        BigDecimal costEntriesInDBSum = getCostEntriesCostsSum(costEntriesInDB);

        if (financialCashExecution.compareTo(costEntriesInDBSum) != 0) {
            if (costEntriesInDBSum.compareTo(BigDecimal.ZERO) == 0) {
                for (Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource : financialSources) {
                    newCostEntries.add(createCostEntry(financialSource, workPackage, costType, params));
                }
            }
//            else {
//                // TODO Если в ИСУПе уже введены суммы исполнения, и они не равны суммам из Эл.Бюджета
//            }
        }

        return newCostEntries;
    }

    private List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> getFinancialSourcesByCode(List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceList, String code) {
        return
                financialSourceList.stream()
                        .filter(ctr -> ctr.getRegProjFinValue().equals(code))
                        .collect(Collectors.toList());
    }

    private BigDecimal getFinancialSourceCBRSum(List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSources) {
        BigDecimal sum = BigDecimal.ZERO;

        if (financialSources != null) {
            sum = financialSources.stream()
                    .map(Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource::getCBR)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return sum;
    }

    private BigDecimal getFinancialSourceCashExecutionSum(List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSources) {
        BigDecimal sum = BigDecimal.ZERO;

        if (financialSources != null) {
            sum = financialSources.stream()
                    .map(Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource::getCashExecution)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return sum;
    }

    private BigDecimal getMaterialBudgetItemConsolidateUnitsSum(List<MaterialBudgetItem> materialBudgetItems) {
        BigDecimal sum = BigDecimal.ZERO;

        if (materialBudgetItems != null) {
            sum = materialBudgetItems.stream()
                    .map(MaterialBudgetItem::getConsolidateUnits)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return sum;
    }

    private BigDecimal getCostEntriesCostsSum(List<CostEntry> costEntries) {
        BigDecimal sum = BigDecimal.ZERO;

        if (costEntries != null) {
            sum = costEntries.stream()
                    .map(CostEntry::getCosts)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return sum;
    }

    private Map<String, CostTypes> getMapCostTypes(){
        // 220 - бюджет субъекта
        // 221 - федеральный бюджет
        // 250 - внебюджетные источники
        // 230 - свод бюджетов муниципальных образований
        Map<String, CostTypes> mapCode = new HashMap<>();
        mapCode.put("220", CostTypes.REGIONAL_BUDGET);
        mapCode.put("221", CostTypes.FEDERAL_BUDGET);
        mapCode.put("250", CostTypes.EXTRABUDGETARY_FUNDS);
        mapCode.put("230", CostTypes.MUNICIPAL_BUDGET);
        return mapCode;
    }

    private CostObject getCostObjectByWorkPackage(WorkPackage workPackage, Map<String, Object> params) {
        CostObject costObject = findCostObjectByWorkPackage(workPackage);
        if (costObject == null) {
            costObject = createCostObjectByWorkPackage(workPackage, params);
        }

        return costObject;
    }

    private CostObject createCostObjectByWorkPackage(WorkPackage workPackage, Map<String, Object> params) {
        Long targetId = (Long) params.get("targetId");
        Long authorId = (Long) params.get("authorId");

        CostObject costObject = CostObject.builder()
                                .projectId(workPackage.getProjectId())
                                .authorId(authorId)
                                .subject(workPackage.getSubject())
                                .description("Загружен из Эл. Бюджета")
                                .type("VariableCostObject")
                                .fixedDate(new Date())
                                .createdOn(new Timestamp(System.currentTimeMillis()))
                                .updatedOn(new Timestamp(System.currentTimeMillis()))
                                .targetId(targetId)
                                .build();

        costObjectRepo.save(costObject);
        return costObject;
    }

    private MaterialBudgetItem createMaterialBudgetItem(Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource,
                                                        CostTypes costType, CostObject costObject) {
        return MaterialBudgetItem.builder()
                .costObjectId(costObject.getId())
                .units(1.0)
                .costTypeId(costType.getValue())
                .comments("Загружен из Эл. Бюджета")
                .budget(financialSource.getCBR())
                .passportUnits(financialSource.getCBR()) // не выгружается из ЭлБюджета
                .consolidateUnits(financialSource.getCBR())
                .planYear(Calendar.getInstance().get(Calendar.YEAR)) // пока текущий год
                .build();

    }
    private CostEntry createCostEntry(Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource,
                                      WorkPackage workPackage, CostTypes costType, Map<String, Object> params) {

        Long authorId = (Long) params.get("authorId");

        return CostEntry.builder()
                .userId(authorId)
                .projectId(workPackage.getProjectId())
                .workPackageId(workPackage.getId())
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

    }

    private CostObject findCostObjectByWorkPackage(WorkPackage workPackage) {
        Long costObjectId = workPackage.getCostObjectId();
        return costObjectRepo.findById(costObjectId).orElse(null);
    }

    private List<MaterialBudgetItem> findMaterialBudgetItemsByCostObjectAndCostType(CostObject costObject, CostTypes costType) {
        return materialBudgetItemRepo.findAllByCostObjectIdAndAndCostTypeId(costObject.getId(), costType.getValue()).orElse(null);
    }

    private List<CostEntry> findCostEntriesByCostObjectAndCostTypes(CostObject costObject, CostTypes costType) {
        List<WorkPackage> workPackagesWithCostObject = workPackageRepo.findAllByCostObjectId(costObject.getId());
        List<Long> workPackageIds = workPackagesWithCostObject.stream()
                                    .map(WorkPackage::getId)
                                    .collect(Collectors.toList());
        return costEntryRepo.findAllByWorkPackageIdsAnAndCostTypeId(costType.getValue(), workPackageIds);
    }

}
