package ru.sibdigital.jopsd.controller.page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.jopsd.config.user.details.CustomUserDetails;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.model.opsd.Page;
import ru.sibdigital.jopsd.model.opsd.PageFile;
import ru.sibdigital.jopsd.model.opsd.PageMap;

import java.util.List;

@Controller
public class PageController extends SuperController {
    @GetMapping("pages/groups")
    public @ResponseBody List<Page> groups(@RequestParam(name = "id", required = false) Long id) {
        return pageService.getGroups(id);
    }

    @PostMapping("/pages/upsert")
    public @ResponseBody ResponseEntity upsertPage(@RequestBody Page body, @AuthenticationPrincipal CustomUserDetails user) {
        try {
            return ResponseEntity.ok(pageService.createOrUpdatePage(body, user.getUser()));
        }
        catch (Exception e){
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/page_maps/upsert")
    public @ResponseBody Object upsertPageMap(@RequestBody PageMap body) {
        try {
            return pageMapService.createOrUpdatePageMap(body);
        }
        catch (Exception e){
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/page_files/upsert")
    public @ResponseBody Object upsertPageFile(@RequestBody PageFile body) {
        try {
            return pageFileService.createOrUpdatePageFile(body);
        }
        catch (Exception e){
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \"" + e.getMessage() + "\"}");
        }
    }
}
