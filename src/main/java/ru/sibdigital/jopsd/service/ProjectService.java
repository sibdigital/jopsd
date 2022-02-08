package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.model.opsd.*;

import java.util.List;

public interface ProjectService {
    List<Project> getProjects();
    Project createDefaultProject(String projectName);
    List<EnabledModule> createDefaultEnabledModules(Project project);
    Board createDefaultBoard(Project project);
    Wiki createDefaultWiki(Project project);
    List<ProjectType> createDefaultProjectTypes(Project project);
    Project archiveProject(Long id);
    Project unarchiveProject(Long id);
}
