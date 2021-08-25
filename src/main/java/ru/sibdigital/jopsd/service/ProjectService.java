package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.model.*;

import java.util.List;

public interface ProjectService {
    List<Project> getProjects();
    Project createDefaultProject(String projectName);
    List<EnabledModule> createDefaultEnabledModules(Long projectId);
    Board createDefaultBoard(Long projectId);
    Wiki createDefaultWiki(Long projectId);
    List<ProjectType> createDefaultProjectTypes(Long projectId);
}
