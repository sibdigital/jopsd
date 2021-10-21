package ru.sibdigital.jopsd.controller.opsd;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.model.opsd.Lbo;
import ru.sibdigital.jopsd.utils.DataFormatUtils;

import java.util.Map;

@Controller
public class LboController extends SuperController {

    @PostMapping("/lbo/save")
    public @ResponseBody Object saveLbo(@RequestBody Lbo lbo) {
        Map<Object, Object> result;
        try {
            return lboService.save(lbo);
        }
        catch (Exception e){
            logError(e);
            String message =  e.getMessage() == null ? "" : e.getMessage();
            result = (Map.of("status", "server", "name", "Ошибка сохранения", "cause", message));
        }
        return DataFormatUtils.buildInternalServerErrorResponse(result);
    }

    @PostMapping("/lbo/delete")
    public @ResponseBody
    ResponseEntity deleteLbo(@RequestParam("lboId") Long lboId) {
        Map<Object, Object> result;
        try {
            lboService.delete(lboId);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            logError(e);
            String message =  e.getMessage() == null ? "" : e.getMessage();
            result = (Map.of("status", "server", "name", "Ошибка сохранения", "cause", message));
        }
        return DataFormatUtils.buildInternalServerErrorResponse(result);
    }
}
