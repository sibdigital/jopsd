package ru.sibdigital.jopsd.service.opsd;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sibdigital.jopsd.model.opsd.CostType;
import ru.sibdigital.jopsd.model.opsd.EbCostType;
import ru.sibdigital.jopsd.model.opsd.RegEbCostType;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Service
public class CostTypeServiceImpl extends SuperServiceImpl implements CostTypeService {
    @Override
    public CostType save(CostType costType) {
        return costTypeRepository.save(costType);
    }

    @Override
    public List<EbCostType> getEbCostTypesByCostTypeId(Long costTypeId) {
        return ebCostTypeRepository.getEbCostTypesByCostTypeId(costTypeId);
    }

    @Override
    public List<RegEbCostType> getAllWithAdditionalEbCostTypes() {
        List<RegEbCostType> regEbCostTypes = regEbCostTypeRepository.findAll();
        List<EbCostType> freeEbCostTypeList = ebCostTypeRepository.getFreeEbCostTypes();
        freeEbCostTypeList.forEach(
                ebCostType -> {
                    RegEbCostType newRegEbCost = new RegEbCostType();
                    newRegEbCost.setEbCostType(ebCostType);
                    regEbCostTypes.add(newRegEbCost);
                }
        );
        return regEbCostTypes;
    }

    @Transactional
    @Override
    public void saveRegEbCostTypesForCostType(List<EbCostType> ebCostTypeList, CostType costType) {
        regEbCostTypeRepository.deleteAllByCostType(costType);
        List<RegEbCostType> list = new ArrayList<>();
        ebCostTypeList.forEach(
                ebCostType -> {
                    RegEbCostType regEbCostType = RegEbCostType.builder()
                                                    .costType(costType)
                                                    .ebCostType(ebCostType)
                                                    .build();
                    list.add(regEbCostType);
                }
        );
        regEbCostTypeRepository.saveAll(list);
    }
}
