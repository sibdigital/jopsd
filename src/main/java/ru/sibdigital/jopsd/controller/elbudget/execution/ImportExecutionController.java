package ru.sibdigital.jopsd.controller.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.dto.TargetMatch;
import ru.sibdigital.jopsd.model.opsd.CostObject;
import ru.sibdigital.jopsd.model.opsd.Target;
import ru.sibdigital.jopsd.model.opsd.User;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

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
    @PostMapping("/import/execution")
    public ResponseEntity<String> importExecution(
                                            @RequestParam("file") MultipartFile multipartFile,
                                            @RequestParam("workPackageId") Long workPackageId
//                                            @RequestParam("authorId") Long authorId
                                            ) {
        try {
            InputStream inputStream = multipartFile.getInputStream();

            Map<String, Object> params = new HashMap<>();
            params.put("workPackageId", workPackageId);
//            params.put("authorId", authorId);
            params.put("authorId", Long.valueOf(2));

            executionService.importFile(inputStream, params);

            return ResponseEntity.ok()
                    .body("{\"cause\": \"Файл успешно загружен\"," +
                            "\"status\": \"server\"," +
                            "\"sname\": \"" + multipartFile.getOriginalFilename() + "\"}");
//                            "}");
        }
        catch (Exception e) {
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \"" + multipartFile.getOriginalFilename() + "\"}");
//                            "}");
        }
    }

//    @PostMapping("/import/elbudget")
    @GetMapping("/import/execution")
    public ResponseEntity<String> importElBudget(
//            @RequestParam("file") MultipartFile multipartFile
                                            @RequestParam("workPackageId") Long workPackageId,
                                            @RequestParam("authorId") Long authorId
    ) {
        try {
//            InputStream inputStream = multipartFile.getInputStream();
            File file = new File("D:/sibdigital/xml/New_workplaces.xml"); // Для теста Post на GetMapping
            InputStream inputStream = new FileInputStream(file);

            Map<String, Object> params = new HashMap<>();
//            params.put("workPackageId", workPackageId);
//            params.put("authorId", authorId);

            executionService.importFile(inputStream, params);

            return ResponseEntity.ok()
                    .body("{\"cause\": \"Файл успешно загружен\"," +
                            "\"status\": \"server\"," +
//                            "\"sname\": \"" + multipartFile.getOriginalFilename() + "\"}");
                            "}");
        }
        catch (Exception e) {
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
//                            "\"sname\": \"" + multipartFile.getOriginalFilename() + "\"}");
                            "}");
        }
    }


    @PostMapping(value = "/import/execution/find_work_package")
    public @ResponseBody Object findWorkPackage(@RequestParam("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            WorkPackage workPackage = executionService.findWorkPackage(inputStream);

            if (workPackage == null) {
                return ResponseEntity.ok()
                        .body("{\"status\": \"server\"," +
                                "\"cause\":\"Мероприятие не найдено. Выберите вручную или создайте\"," +
                                "\"sname\": \"null\"}");
            } else {
                return workPackage;
            }
        }
        catch (Exception e) {
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping(value = "/import/execution/create_work_package")
    public @ResponseBody Object createWorkPackage(@RequestParam("file") MultipartFile multipartFile,
                                                  @RequestParam("workPackageName") String workPackageName,
                                                  @RequestParam("projectId") Long projectId,
                                                  @RequestParam("projectName") String projectName,
                                                  @RequestParam("authorId") Long authorId) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            WorkPackage workPackage = executionService.createWorkPackage(inputStream, workPackageName, projectId, projectName, authorId);

            if (workPackage == null) {
                return ResponseEntity.ok()
                        .body("{\"status\": \"server\"," +
                                "\"cause\":\"Ошибка сохранения\"," +
                                "\"sname\": \"" + workPackageName + "\"}");
            } else {
                return workPackage;
            }
        }
        catch (Exception e) {
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/import/execution/save_finance")
    public @ResponseBody Object saveFinance(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("workPackageId") Long workPackageId,
            @RequestParam("authorId") Long authorId) {
        try {
            InputStream inputStream = multipartFile.getInputStream();

            Map<String, Object> params = new HashMap<>();
            params.put("workPackageId", workPackageId);
            params.put("authorId", authorId);

            CostObject costObject = financialService.saveFinances(inputStream, params);

            if (costObject != null) {
                return costObject;
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("{\"status\": \"server\"," +
                                "\"cause\":\"Ошибка сохранения\"," +
                                "\"sname\": \"" + multipartFile.getOriginalFilename() + "\"}");
            }
        }
        catch (Exception e) {
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \"" + multipartFile.getOriginalFilename() + "\"}");
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \"" + multipartFile.getOriginalFilename() + "\"}");
        }
    }

    @PostMapping("/import/execution/process_targets")
    public @ResponseBody Object processPurposeCriteria(@RequestBody List<TargetMatch> targetMatches,
                                                     @RequestParam("workPackageId") Long workPackageId,
                                                     @RequestParam("authorId") Long authorId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("workPackageId", workPackageId);
            params.put("authorId", authorId);
            List<TargetMatch> targetMatchesAfterProcess = targetService.createAndSaveTargetValues(targetMatches, params);
            return targetMatchesAfterProcess;
        }
        catch (Exception e) {
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \" work-package-id:" + workPackageId + "\"}");
        }
    }


    @GetMapping("/import/execution1")
    public String getTest() {
        return "";
    }

    @GetMapping("/cost_objects_all")
    public @ResponseBody
    List<CostObject> getCostObject(){
        return costObjectRepo.findAll();
    }

//    @GetMapping("/project_list")
//    public @ResponseBody
//    List<Project> getProjects(){
//        List<Project> projects = projectService.getProjects();
//        projects.sort(Comparator.comparing(Project::getName));
//        return projects;
//    }

//    @GetMapping("/work_package_list")
//    public @ResponseBody
//    List<WorkPackage> getWorkPackages(@RequestParam("projectId") Long projectId){ //TODO В service перенести
//        List<WorkPackage> workPackages = workPackageRepo.findAllByProjectId(projectId);
//        workPackages.sort(Comparator.comparing(WorkPackage::getSubject));
//        return workPackages;
//    }

    @GetMapping("/target_list")
    public @ResponseBody
    List<Target> getTargets(@RequestParam("projectId") Long projectId){ //TODO В service перенести
        List<Target> targets = targetRepo.findAllByProjectId(projectId);
        targets.sort(Comparator.comparing(Target::getName));
        return targets;
    }
}
