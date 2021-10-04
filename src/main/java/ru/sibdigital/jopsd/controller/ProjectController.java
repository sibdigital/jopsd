package ru.sibdigital.jopsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.model.opsd.Project;

import java.util.List;

@Controller
public class ProjectController extends SuperController{
    @GetMapping("/projects/all")
    public @ResponseBody List<Project> getAllProjects() {
        return projectService.getProjects();
    }
}