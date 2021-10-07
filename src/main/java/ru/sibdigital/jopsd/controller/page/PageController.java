package ru.sibdigital.jopsd.controller.page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.jopsd.config.user.details.CustomUserDetails;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.model.opsd.Page;
import ru.sibdigital.jopsd.model.opsd.PageFile;
import ru.sibdigital.jopsd.model.opsd.PageMap;
import ru.sibdigital.jopsd.model.opsd.User;
import ru.sibdigital.jopsd.utils.DataFormatUtils;

import java.util.List;
import java.util.Map;

@Controller
public class PageController extends SuperController {
    @GetMapping("pages/groups")
    public @ResponseBody List<Page> groups() {
        return pageService.getGroups();
    }

    @PostMapping("/pages/upsert")
    public @ResponseBody ResponseEntity upsertPage(@RequestBody Page body, @AuthenticationPrincipal CustomUserDetails user) {
        try {
            return ResponseEntity.ok(pageService.createOrUpdatePage(body, user.getUser()));
        }
        catch (Exception e){
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/pages/{id_page}/delete")
    public @ResponseBody Object deletePage(@PathVariable("id_page") Long pageId) {
        try {
            return pageService.deletePage(pageId);
        }
        catch (Exception e){
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/page_maps/new")
    public @ResponseBody Object createPageMap(@RequestBody PageMap body) {
        try {
            return pageMapService.createOrUpdatePageMap(body);
        }
        catch (Exception e){
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/page_maps/{id_page_map}/edit")
    public @ResponseBody Object updatePageMap(@RequestBody PageMap body, @PathVariable("id_page_map") Long pageMapId) {
        try {
            return pageMapService.createOrUpdatePageMap(body);
        }
        catch (Exception e){
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/page_maps/{id_page_map}/delete")
    public @ResponseBody Object deletePageMap(@PathVariable("id_page_map") Long pageMapId) {
        try {
            return pageMapService.deletePageMap(pageMapId);
        }
        catch (Exception e){
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/page_files/new")
    public @ResponseBody Object createPageFile(@RequestBody PageFile body) {
        try {
            return pageFileService.createOrUpdatePageFile(body);
        }
        catch (Exception e){
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/page_files/{id_page_file}/edit")
    public @ResponseBody Object updatePageFile(@RequestBody PageFile body, @PathVariable("id_page_file") Long pageFileId) {
        try {
            return pageFileService.createOrUpdatePageFile(body);
        }
        catch (Exception e){
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/page_files/{id_page_file}/delete")
    public @ResponseBody Object deletePageFile(@PathVariable("id_page_file") Long pageFileId) {
        try {
            return pageFileService.deletePageFile(pageFileId);
        }
        catch (Exception e){
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }
}
