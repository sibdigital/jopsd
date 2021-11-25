package ru.sibdigital.jopsd.service.opsd;

import ru.sibdigital.jopsd.model.opsd.CostType;
import ru.sibdigital.jopsd.model.opsd.EbCostType;
import ru.sibdigital.jopsd.model.opsd.RegEbCostType;

import java.util.List;

public interface CostTypeService {
    CostType save(CostType costType);
    List<EbCostType> getEbCostTypesByCostTypeId(Long costTypeId);
    List<RegEbCostType> getAllWithAdditionalEbCostTypes();
    void saveRegEbCostTypesForCostType(List<EbCostType> ebCostTypeList, CostType costType);
}
