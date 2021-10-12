package ru.sibdigital.jopsd.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.model.opsd.Project;
import ru.sibdigital.jopsd.repository.opsd.ProjectRepo;

import java.util.List;

@Controller
public class ProjectController extends SuperController{

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping("/projects/all")
    public @ResponseBody List<Project> getAllProjects() {
        return projectService.getProjects();
    }
    @GetMapping("/projects/all/{user_id}")
    public @ResponseBody List<Project> getProjectsByUserId(@PathVariable ("user_id") Long id) {
        return projectRepo.findProjectsByUserRoles(id);
    }
}
