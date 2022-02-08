package ru.sibdigital.jopsd.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.model.opsd.Project;

import java.util.List;

@Controller
public class ProjectController extends SuperController{

    @GetMapping("/projects/all")
    public @ResponseBody List<Project> getAllProjects() {
        return projectService.getProjects();
    }

    @GetMapping("/projects/{id}/archive")
    public @ResponseBody Project archiveProject(@PathVariable("id") Long id) {
        return projectService.archiveProject(id);
    }

    @GetMapping("/projects/{id}/unarchive")
    public @ResponseBody Project unarchiveProject(@PathVariable("id") Long id) {
        return projectService.unarchiveProject(id);
    }
}
