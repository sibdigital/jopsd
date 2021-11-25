package ru.sibdigital.jopsd.controller.opsd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.dto.CostTypeDto;
import ru.sibdigital.jopsd.model.opsd.CostType;
import ru.sibdigital.jopsd.model.opsd.EbCostType;
import ru.sibdigital.jopsd.model.opsd.RegEbCostType;
import ru.sibdigital.jopsd.utils.DataFormatUtils;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class CostTypeController extends SuperController {

    @PostMapping("/costType/save")
    public @ResponseBody
    Object saveLbo(@RequestBody CostTypeDto costTypeDto) {
        Map<Object, Object> result;
        try {
            CostType costType = costTypeService.save(costTypeDto.getCostType());
            rateService.save(costTypeDto.getRateList(), costType);
            costTypeService.saveRegEbCostTypesForCostType(costTypeDto.getEbCostTypeList(), costType);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            log.error("Ошибка сохранения costType. {}", e.getMessage());
            String message =  e.getMessage() == null ? "" : e.getMessage();
            result = (Map.of("status", "server", "name", "Ошибка сохранения", "cause", message));
        }
        return DataFormatUtils.buildInternalServerErrorResponse(result);
    }

    @PostMapping("/costType/delete")
    public @ResponseBody
    ResponseEntity deleteLbo(@RequestParam("rateId") Long rateId) {
        Map<Object, Object> result;
        try {
            rateService.delete(rateId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            log.error("Ошибка удаления costType. {}", e.getMessage());
            String message =  e.getMessage() == null ? "" : e.getMessage();
            result = (Map.of("status", "server", "name", "Ошибка сохранения", "cause", message));
        }
        return DataFormatUtils.buildInternalServerErrorResponse(result);
    }
    @GetMapping("/ebCostTypesByCostTypeId")
    public @ResponseBody
    List<EbCostType> getEbCostTypesByCostTypeId(@RequestParam("costTypeId") Long costTypeId) {
        return costTypeService.getEbCostTypesByCostTypeId(costTypeId);
    }
    @GetMapping("/rebCostTypesWithAdditionalEbCT")
    public @ResponseBody
    List<RegEbCostType> getEbCostTypesMap() {
       return costTypeService.getAllWithAdditionalEbCostTypes();
    }
}
