package ru.sibdigital.jopsd.controller.mp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.model.Project;
import ru.sibdigital.jopsd.model.WorkPackage;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class MPImportController extends SuperController {

    @GetMapping("/upload_mpp")
    public String viewAppointment(Model model, HttpSession session) {
       List<Project> projectList = projectService.getProjectList();
        model.addAttribute("projectList", projectList);
        return "upload_mpp";
    }

    @PostMapping("/import/mpp")
    public RedirectView importMpp(@RequestParam("file") MultipartFile multipartFile,
                                            @RequestParam("projectId") Long projectId,
                                            RedirectAttributes redirectAttributes
//                                            @RequestParam("authorId") Long authorId
    ) {
        try {
            InputStream inputStream = multipartFile.getInputStream();

            Map<String, Object> params = new HashMap<>();
            params.put("projectId", projectId);
//            params.put("authorId", authorId);
            params.put("authorId", Long.valueOf(2));

            List<WorkPackage> workPackages = MPService.importFile(inputStream, params);
            redirectAttributes.addAttribute("work_packages", workPackages);

            return new RedirectView("uploaded_work_packages");

        } catch (Exception e) {
            logError(e);
            return null;
        }
    }

    @GetMapping(value = "/import/uploaded_work_packages")
    public String showUploadedWorkPackages(@RequestParam("work_packages") List<WorkPackage> workPackages,
                                           Model model) {
        model.addAttribute("work_packages", workPackages);
        model.addAttribute("ref_portal", applicationConstants.getRefTestPortal());
//        model.addAttribute("ref_portal", applicationConstants.getRefWorkingPortal);
        return "uploaded_work_packages";
    }
}
