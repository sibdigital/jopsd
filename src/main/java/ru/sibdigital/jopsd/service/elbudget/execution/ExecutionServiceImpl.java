package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.enums.Statuses;
import ru.sibdigital.jopsd.model.enums.Types;
import ru.sibdigital.jopsd.model.opsd.*;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExecutionServiceImpl extends SuperServiceImpl implements ExecutionService {

    @Override
    public void importFile(InputStream inputStream, Map<String, Object> params) throws Exception {
        Unmarshaller unmarshaller = getUnmarshaller(Resultsexecution.class);
        if (unmarshaller == null) {
            throw new Exception("Не удалось создать демаршаллизатор");
        }

        Resultsexecution resultsExecution = (Resultsexecution) unmarshaller.unmarshal(inputStream);
        WorkPackage workPackage = getWorkPackage(params);
        processResultExecution(resultsExecution, workPackage, params);
    }

    @Override
    public Long getResultMetaIdInInputStream(InputStream inputStream) throws Exception {
        Long resultMetaId = null;

        Resultsexecution resultsExecution = executionParseService.unmarshalInputStream(inputStream);
        Resultsexecution.RegProject.Results.Result result = executionParseService.getResult(resultsExecution);
        if (result != null) {
            resultMetaId = result.getResultMetaId();
        }

        return resultMetaId;
    }

    @Override
    public WorkPackage findWorkPackage(InputStream inputStream) throws Exception {
        WorkPackage workPackage = null;

        try {
            Long resultMetaId = getResultMetaIdInInputStream(inputStream);
            if (resultMetaId != null) {
                workPackage = workPackageRepo.findWorkPackageByMetaId(resultMetaId).orElse(null);
            }
        } catch (Exception e) {
            throw e;
        }

        return workPackage;
    }

    @Override
    public WorkPackage createWorkPackage(InputStream inputStream, String workPackageName, Long projectId, String projectName, Long authorId) throws Exception {
        if (projectId == 0) {
            Project project = createProjectWithSettings(projectName);
            projectId = project.getId();
        }

        Long resultMetaId = getResultMetaIdInInputStream(inputStream);

        WorkPackage workPackage = WorkPackage.builder()
//                                    .typeId(Types.EVENT.getValue())
//                                    .projectId(projectId)
                                    .subject(workPackageName)
//                                    .statusId(Statuses.IN_WORK.getValue())
//                                    .authorId(authorId)
                                    .lockVersion(Long.valueOf(0))
                                    .doneRatio(Long.valueOf(0))
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .updatedAt(Timestamp.from(Instant.now()))
                                    .metaId(resultMetaId)
                                    .build();
        workPackageRepo.save(workPackage);
        return workPackage;
    }

    private Project createProjectWithSettings(String projectName) {

        Project project = projectService.createDefaultProject(projectName);
        projectRepo.save(project);

        List<EnabledModule> enabledModuleList = projectService.createDefaultEnabledModules(project.getId());
        Board board = projectService.createDefaultBoard(project.getId());
        Wiki wiki = projectService.createDefaultWiki(project.getId());
        List<ProjectType> types = projectService.createDefaultProjectTypes(project.getId());
        enabledModuleRepo.saveAll(enabledModuleList);
        boardRepo.save(board);
        wikiRepo.save(wiki);
        projectTypeRepo.saveAll(types);

        return project;
    }

    private void processResultExecution(Resultsexecution resultsExecution, WorkPackage workPackage, Map<String, Object> params) {
        Resultsexecution.RegProject regProject = resultsExecution.getRegProject();

        if (regProject != null) {
//            riskService.saveRisks(regProject, params);
//            targetService.saveTargets(regProject, workPackage, params);
//            financialService.saveFinances(regProject, workPackage, params);
        }
    }


    private WorkPackage getWorkPackage(Map<String, Object> params) {
        Long workPackageId = (Long) params.get("workPackageId");
        WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);
        return  workPackage;
    }

    @Override
    public void matchData(InputStream inputStream, Map<String, Object> params) throws Exception {
        Resultsexecution resultsExecution = executionParseService.unmarshalInputStream(inputStream);

    }
}
