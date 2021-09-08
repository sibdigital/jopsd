package ru.sibdigital.jopsd.controller.mp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.model.opsd.Project;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;
import ru.sibdigital.jopsd.service.ProjectService;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MPImportController extends SuperController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/import/mpp")
    public @ResponseBody Object importMpp(@RequestParam("file") MultipartFile multipartFile,
                             @RequestParam("projectId") Long projectId,
                             @RequestParam("authorId") Long authorId
    ) {
        try {
            InputStream inputStream = multipartFile.getInputStream();

            Map<String, Object> params = new HashMap<>();
            params.put("projectId", projectId);
            params.put("authorId", authorId);

            List<WorkPackage> workPackages = MPService.importFile(inputStream, params);
            return workPackages;

        } catch (Exception e) {
            logError(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"server\"," +
                            "\"cause\":\"Ошибка сохранения\"," +
                            "\"sname\": \" project-id:" + projectId + "\"}");
        }
    }

}
