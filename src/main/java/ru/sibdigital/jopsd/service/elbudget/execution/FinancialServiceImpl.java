package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.enums.CostTypes;
import ru.sibdigital.jopsd.model.opsd.*;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinancialServiceImpl extends SuperServiceImpl implements FinancialService {

    @Override
    public CostObject saveFinances(InputStream inputStream, Map<String, Object> params) throws Exception {
        CostObject costObject = null;

        Long workPackageId = (Long) params.get("workPackageId");
        Long authorId = (Long) params.get("authorId");

        WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);
        User author = userRepository.findById(authorId).orElse(null);

        Resultsexecution resultsExecution = executionParseService.unmarshalInputStream(inputStream);
        Resultsexecution.RegProject.Results.Result result = executionParseService.getResult(resultsExecution);
        if (result != null) {
            costObject = costObjectRepo.findCostObjectByMetaId(result.getResultMetaId()).orElse(null);
            if (costObject == null) {
                params.put("resultMetaId", result.getResultMetaId());
                costObject = createCostObjectByWorkPackageAndResultMetaId(workPackage, author, params);
            }

            List<MaterialBudgetItem> newMaterialBudgetItemList = new ArrayList<>();
            List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceList = executionParseService.getFinancialSourceList(resultsExecution.getRegProject());
            if (financialSourceList != null) {
                Map<String, CostTypes> mapCostTypes = getMapCostTypes();
                List<String> codes = new ArrayList<>(mapCostTypes.keySet());
                for (String code : codes) {
                    Long costTypeId = mapCostTypes.get(code).getValue();
                    CostType costType = costTypeRepository.findById(costTypeId).orElse(null);

                    List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSources =
                            getFinancialSourcesByCode(financialSourceList, code);

                    List<MaterialBudgetItem> materialBudgetItems = parseMaterialBudgetItems(financialSources, costType, costObject);
                    newMaterialBudgetItemList.addAll(materialBudgetItems);
                }
            }

            List<MaterialBudgetItem> oldMaterialBudgetItems = materialBudgetItemRepo.findAllByCostObjectId(costObject.getId()).orElse(null);
            if (oldMaterialBudgetItems != null) {
                materialBudgetItemRepo.deleteAll(oldMaterialBudgetItems);
            }

            materialBudgetItemRepo.saveAll(newMaterialBudgetItemList);
        }

        return costObject;
    }

    private List<MaterialBudgetItem> parseMaterialBudgetItems(List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSources,
                                          CostType costType, CostObject costObject) {
        List<MaterialBudgetItem> newMaterialBudgetItems = new ArrayList<>();

        for (Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource : financialSources) {
            newMaterialBudgetItems.add(createMaterialBudgetItem(financialSource, costType, costObject));
        }

        return newMaterialBudgetItems;
    }

    private List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> getFinancialSourcesByCode(List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceList, String code) {
        return
                financialSourceList.stream()
                        .filter(ctr -> ctr.getRegProjFinValue().equals(code))
                        .collect(Collectors.toList());
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

    private CostObject createCostObjectByWorkPackageAndResultMetaId(WorkPackage workPackage, User author, Map<String, Object> params) {
        Long resultMetaId = (Long) params.get("resultMetaId");

        CostObject costObject = CostObject.builder()
                                .project(workPackage.getProject())
                                .author(author)
                                .subject(workPackage.getSubject())
                                .description("Загружен из Эл. Бюджета")
                                .type("VariableCostObject")
                                .fixedDate(LocalDateTime.now())
                                .createdOn(new Timestamp(System.currentTimeMillis()))
                                .updatedOn(new Timestamp(System.currentTimeMillis()))
                                .metaId(resultMetaId)
//                                .targetId(targetId) // Пока не заполняем
                                .build();

        costObjectRepo.save(costObject);

        workPackage.setCostObject(costObject);
        workPackageRepo.save(workPackage);

        return costObject;
    }

    private MaterialBudgetItem createMaterialBudgetItem(Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource,
                                                        CostType costType, CostObject costObject) {
        BigDecimal cbr = (financialSource.getCBR() != null) ? financialSource.getCBR().multiply(BigDecimal.valueOf(1000)) : BigDecimal.valueOf(0);
        return MaterialBudgetItem.builder()
                .costObject(costObject)
                .units(1.0)
                .costType(costType)
                .comments("Загружен из Эл. Бюджета")
                .budget(cbr)
                .passportUnits(cbr) // не выгружается из ЭлБюджета
                .consolidateUnits(cbr)
                .planYear(Calendar.getInstance().get(Calendar.YEAR)) // пока текущий год
                .build();

    }
    private CostEntry createCostEntry(Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource financialSource,
                                      WorkPackage workPackage, CostTypes costType, Map<String, Object> params) {

        Long authorId = (Long) params.get("authorId");
        Date spentOn = new Date(); //TODO какая дата?

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(spentOn);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH) + 1;
        Integer week = calendar.get(Calendar.WEEK_OF_YEAR);

        User author = userRepository.findById(authorId).orElse(null);
        CostType costTypeObject = costTypeRepository.findById(costType.getValue()).orElse(null);

        return CostEntry.builder()
                .user(author)
                .project(workPackage.getProject())
                .workPackage(workPackage)
                .costType(costTypeObject)
                .units(financialSource.getCashExecution().doubleValue())
                .costs(financialSource.getCashExecution())
                .recordedLiability(financialSource.getBudgetCommitments())
                .spentOn(LocalDateTime.now())
                .createdOn(new Timestamp(System.currentTimeMillis()))
                .updatedOn(new Timestamp(System.currentTimeMillis()))
                .comments("Загружено из Эл. Бюджета")
                .blocked(false)
                .tyear(year)
                .tmonth(month)
                .tweek(week)
                .build();

    }

    private List<CostEntry> findCostEntriesByCostObjectAndCostTypes(CostObject costObject, CostTypes costType) {
        List<WorkPackage> workPackagesWithCostObject = workPackageRepo.findAllByCostObjectId(costObject.getId());
        List<Long> workPackageIds = workPackagesWithCostObject.stream()
                                    .map(WorkPackage::getId)
                                    .collect(Collectors.toList());
        return costEntryRepo.findAllByWorkPackageIdsAndCostTypeId(workPackageIds, costType.getValue());
    }

    //---------------- НЕ ИСПОЛЬЗУЕМЫЕ ФУНКЦИИ

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

    private BigDecimal getMaterialBudgetItemConsolidateUnitsSum(List<MaterialBudgetItem> materialBudgetItems) {
        BigDecimal sum = BigDecimal.ZERO;

        if (materialBudgetItems != null) {
            sum = materialBudgetItems.stream()
                    .map(MaterialBudgetItem::getConsolidateUnits)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return sum;
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

    private CostObject findCostObjectByWorkPackage(WorkPackage workPackage) {
        return workPackage.getCostObject();
    }

    private List<MaterialBudgetItem> findMaterialBudgetItemsByCostObjectAndCostType(CostObject costObject, CostTypes costType) {
        return materialBudgetItemRepo.findAllByCostObjectIdAndCostTypeId(costObject.getId(), costType.getValue()).orElse(null);
    }


}
