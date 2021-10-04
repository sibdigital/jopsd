package ru.sibdigital.jopsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

import java.util.List;

@Controller
public class WorkPackageController extends SuperController{
    @GetMapping("work_packages/all")
    public @ResponseBody List<WorkPackage> getAllWorkPackagesByProjectId(@RequestParam("projectId") Long projectId){
        return workPackageService.getWorkPackagesByProject(projectId);
    }
}
