package ru.sibdigital.jopsd.controller.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sibdigital.jopsd.config.user.details.CustomUserDetails;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.dto.TargetMatch;
import ru.sibdigital.jopsd.model.opsd.CostObject;
import ru.sibdigital.jopsd.model.opsd.Target;
import ru.sibdigital.jopsd.model.opsd.User;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;
import ru.sibdigital.jopsd.utils.DataFormatUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ImportExecutionController extends SuperController {
    @PostMapping(value = "/import/execution/find_work_package")
    public @ResponseBody Object findWorkPackage(@RequestParam("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            WorkPackage workPackage = executionService.findWorkPackage(inputStream);

            if (workPackage == null) {
                return DataFormatUtils.buildOkResponse(Map.of("status", "server", "name", "null", "cause", "Мероприятие не найдено. Выберите вручную или создайте"));
            } else {
                return workPackage;
            }
        }
        catch (Exception e) {
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping(value = "/import/execution/put_metaid_to_work_package")
    public @ResponseBody Object putMetaidToWorkPackage(@RequestParam("file") MultipartFile multipartFile,
                                                  @RequestParam("workPackageId") Long workPackageId,
                                                  HttpSession session) {
        try {
            CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = currentUser.getUser();

            InputStream inputStream = multipartFile.getInputStream();
            WorkPackage workPackage = executionService.putMetaIdToWorkPackage(inputStream, workPackageId);

            if (workPackage == null) {
                return DataFormatUtils.buildOkResponse(Map.of("status", "server", "name", "null", "cause", "Не найден по id workPackage"));
            } else {
                return workPackage;
            }
        }
        catch (Exception e) {
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping(value = "/import/execution/create_work_package")
    public @ResponseBody Object createWorkPackage(@RequestParam("file") MultipartFile multipartFile,
                                                  @RequestParam("workPackageName") String workPackageName,
                                                  @RequestParam("projectId") Long projectId,
                                                  @RequestParam("projectName") String projectName,
                                                  HttpSession session) {
        try {
            CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = currentUser.getUser();

            InputStream inputStream = multipartFile.getInputStream();
            WorkPackage workPackage = executionService.createWorkPackage(inputStream, workPackageName, projectId, projectName, user.getId());

            if (workPackage == null) {
                return DataFormatUtils.buildOkResponse(Map.of("status", "server", "name", "null", "cause", "Ошибка сохранения"));
            } else {
                return workPackage;
            }
        }
        catch (Exception e) {
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/import/execution/save_finance")
    public @ResponseBody Object saveFinance(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("workPackageId") Long workPackageId,
            HttpSession session) {
        try {
            CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = currentUser.getUser();

            InputStream inputStream = multipartFile.getInputStream();

            Map<String, Object> params = new HashMap<>();
            params.put("workPackageId", workPackageId);
            params.put("authorId", user.getId());

            CostObject costObject = financialService.saveFinances(inputStream, params);

            if (costObject != null) {
                return costObject;
            } else {
                return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", "Cost object is null"));
            }
        }
        catch (Exception e) {
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/import/execution/match_purpose_criteria")
    public @ResponseBody Object matchPurposeCriteria(@RequestParam("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();

            List<TargetMatch> targetMatches = targetService.matchTargets(inputStream);

            return targetMatches;
        }
        catch (Exception e) {
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @PostMapping("/import/execution/process_targets")
    public @ResponseBody Object processPurposeCriteria(@RequestBody List<TargetMatch> targetMatches,
                                                     @RequestParam("workPackageId") Long workPackageId,
                                                     HttpSession session) {
        try {
            CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = currentUser.getUser();

            Map<String, Object> params = new HashMap<>();
            params.put("workPackageId", workPackageId);
            params.put("authorId", user.getId());
            List<TargetMatch> targetMatchesAfterProcess = targetService.createAndSaveTargetValues(targetMatches, params);
            return targetMatchesAfterProcess;
        }
        catch (Exception e) {
            logError(e);
            String mes =  e.getMessage() == null ? "" : e.getMessage();
            return DataFormatUtils.buildInternalServerErrorResponse(Map.of("status", "server", "name", "Ошибка сохранения", "cause", mes));
        }
    }

    @GetMapping("/cost_objects_all")
    public @ResponseBody
    List<CostObject> getCostObject(){
        return costObjectRepo.findAll();
    }

}
