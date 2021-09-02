package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.enums.ProjectStatuses;
import ru.sibdigital.jopsd.model.opsd.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ProjectServiceImpl extends SuperServiceImpl implements ProjectService{
    @Override
    public List<Project> getProjects() {
        return projectRepo.findAll();
    }

    @Override
    public Project createDefaultProject(String projectName) {
        Long lft = getProjectMaxLft();

        Project project = Project.builder()
                .name(projectName)
                .description("")
                .createdOn(Timestamp.from(Instant.now()))
                .updatedOn(Timestamp.from(Instant.now()))
                .identifier(transcriptorService.getLatinIdentifier(projectName))
                .isPublic(false)
                .status(ProjectStatuses.STATUS_ACTIVE.getValue())
                .type("project")
                .lft(lft + 1)
                .rgt(lft + 2)
                .build();

        return project;
    }

    @Override
    public List<EnabledModule> createDefaultEnabledModules(Long projectId) {
        List<EnabledModule> list = new ArrayList<>();
        Set<String> defaultProjectsModulesNames = getDefaultProjectsModulesName();
        defaultProjectsModulesNames.forEach(ctr -> {
            list.add(EnabledModule.builder()
//                                    .projectId(projectId)
                                    .name(ctr)
                                    .build());
        });
        return list;
    }

    @Override
    public Board createDefaultBoard(Long projectId) {
        return Board.builder()
//                        .projectId(projectId)
                        .name("Основная дискуссия проекта")
                        .description("Основная дискуссия проекта")
                        .position(1)
                        .isDefault(true)
                        .topicsCount(0)
                        .messagesCount(0)
                        .build();
    }

    @Override
    public Wiki createDefaultWiki(Long projectId) {
        return Wiki.builder()
//                    .projectId(projectId)
                    .startPage("Wiki")
                    .status(1)
                    .createdAt(Timestamp.from(Instant.now()))
                    .updatedAt(Timestamp.from(Instant.now()))
                    .build();
    }


    @Override
    public List<ProjectType> createDefaultProjectTypes(Long projectId) {
        List<ProjectType> list = new ArrayList<>();
        list.add(ProjectType.builder().projectId(projectId).typeId(Long.valueOf(1)).build());
        list.add(ProjectType.builder().projectId(projectId).typeId(Long.valueOf(2)).build());
        list.add(ProjectType.builder().projectId(projectId).typeId(Long.valueOf(3)).build());
        return list;
    }

    private Long getProjectMaxLft() {
        Long lft = Long.valueOf(0);
        Project projectWithMaxLft = projectRepo.findProjectWithMaxLft();
        if (projectWithMaxLft != null) {
            lft = projectWithMaxLft.getLft();
        }
        return lft;
    }

    private Set<String> getDefaultProjectsModulesName() {
        return Set.of(
                        "calendar",
                        "work_package_tracking",
                        "news",
                        "time_tracking",
                        "wiki",
                        "stages",
                        "activities",
                        "arbitary_objects",
                        "backlogs",
                        "boards",
                        "costs_module",
                        "documents",
                        "meetings",
                        "project_risks",
                        "reporting_module",
//                        "reporting_module2",
                        "targets",
                        "work_package_targets",
                        "agreements",
                        "activity",
                        "interactive_map",
                        "strategic_map",
                        "report_progress_project",
                        "report_passport"
        );
    }


}
