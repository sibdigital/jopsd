package ru.sibdigital.jopsd.controller.opsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.utils.DataFormatUtils;

import java.util.Map;

@Controller
public class TargetController extends SuperController {
    @PostMapping("/targets/metaId")
    public @ResponseBody
    Object changeTargetMetaId(@RequestParam(value = "targetId") Long targetId,
                      @RequestParam(value = "metaId", required = false) Long metaId) {
       try {
            return targetService.changeMetaId(targetId, metaId);
        } catch (Exception e){
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("errorMessage", "Не удалось изменить показатель"));
        }
    }
}
