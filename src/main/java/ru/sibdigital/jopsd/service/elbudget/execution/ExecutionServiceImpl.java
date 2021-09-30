package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public WorkPackage putMetaIdToWorkPackage(InputStream inputStream, Long workPackageId) throws Exception {
        WorkPackage workPackage = null;
        try {
            Long resultMetaId = getResultMetaIdInInputStream(inputStream);
            if (resultMetaId != null) {
                workPackage = workPackageService.putMetaId(workPackageId, resultMetaId);
            }
        } catch (Exception e) {
            throw e;
        }

        return workPackage;
    }

    @Override
    @Transactional
    public WorkPackage createWorkPackage(InputStream inputStream, String workPackageName, Long projectId, String projectName, Long authorId) throws Exception {
        if (projectId == 0) {
            Project project = createProjectWithSettings(projectName);
            projectId = project.getId();
        }

        Long resultMetaId = getResultMetaIdInInputStream(inputStream);

        Type type = typeRepository.findById(Types.EVENT.getValue()).orElse(null);
        Project project = projectRepo.findById(projectId).orElse(null);
        Status status = statusRepository.findById(Statuses.IN_WORK.getValue()).orElse(null);
        User author = userRepository.findById(authorId).orElse(null);

        WorkPackage workPackage = WorkPackage.builder()
                                    .type(type)
                                    .project(project)
                                    .subject(workPackageName)
                                    .status(status)
                                    .author(author)
                                    .lockVersion(Long.valueOf(0))
                                    .doneRatio(Long.valueOf(0))
                                    .createdAt(Timestamp.from(Instant.now()))
                                    .updatedAt(Timestamp.from(Instant.now()))
                                    .metaId(resultMetaId)
                                    .build();
        workPackageRepo.save(workPackage);
        return workPackage;
    }

    @Transactional
    Project createProjectWithSettings(String projectName) {

        Project project = projectService.createDefaultProject(projectName);
        projectRepo.save(project);

        List<EnabledModule> enabledModuleList = projectService.createDefaultEnabledModules(project);
        Board board = projectService.createDefaultBoard(project);
        Wiki wiki = projectService.createDefaultWiki(project);
        List<ProjectType> types = projectService.createDefaultProjectTypes(project);
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
