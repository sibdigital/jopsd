package ru.sibdigital.jopsd.controller.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.jopsd.config.user.details.CustomUserDetails;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.model.opsd.Page;
import ru.sibdigital.jopsd.model.opsd.PageFile;
import ru.sibdigital.jopsd.model.opsd.PageMap;
import ru.sibdigital.jopsd.model.opsd.projection.PageShortProjection;
import ru.sibdigital.jopsd.utils.DataFormatUtils;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class PageController extends SuperController {
    @GetMapping("pages/groups")
    public @ResponseBody List<Page> groups(@RequestParam(name = "id", required = false) Long id) {
        return pageService.getGroups(id);
    }

    @GetMapping("pages/list")
    public @ResponseBody List<PageShortProjection> list() {
        return pageService.getList();
    }
    @PostMapping("/pages/upsert")
    public @ResponseBody ResponseEntity upsertPage(@RequestBody Page body, @AuthenticationPrincipal CustomUserDetails user) {
        Map<Object, Object> result;
        try {
            return ResponseEntity.ok(pageService.createOrUpdatePage(body, user.getUser()));
        }
        catch (Exception e){
            log.error("Ошибка изменения page. {}", e.getMessage());
            String message =  e.getMessage() == null ? "" : e.getMessage();
            result = (Map.of("status", "server", "name", "Ошибка сохранения", "cause", message));
        }
        return DataFormatUtils.buildInternalServerErrorResponse(result);
    }

    @PostMapping("/page_maps/upsert")
    public @ResponseBody Object upsertPageMap(@RequestBody PageMap body) {
        Map<Object, Object> result;
        try {
            return pageMapService.createOrUpdatePageMap(body);
        }
        catch (Exception e){
            log.error("Ошибка изменения page_map. {}", e.getMessage());
            String message =  e.getMessage() == null ? "" : e.getMessage();
           result = (Map.of("status", "server", "name", "Ошибка сохранения", "cause", message));
        }
        return DataFormatUtils.buildInternalServerErrorResponse(result);
    }

    @PostMapping("/page_files/upsert")
    public @ResponseBody Object upsertPageFile(@RequestBody PageFile body) {
        Map<Object, Object> result;
        try {
            return pageFileService.createOrUpdatePageFile(body);
        }
        catch (Exception e){
            log.error("Ошибка изменения page_file. {}", e.getMessage());
            String message =  e.getMessage() == null ? "" : e.getMessage();
            result = (Map.of("status", "server", "name", "Ошибка сохранения", "cause", message));
        }
        return DataFormatUtils.buildInternalServerErrorResponse(result);
    }
}
