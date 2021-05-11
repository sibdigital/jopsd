package ru.sibdigital.jopsd.service.elbudget;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimestampFunction;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.model.WorkPackageProblem;
import ru.sibdigital.jopsd.model.enums.WorkPackageProblemStatuses;
import ru.sibdigital.jopsd.model.enums.WorkPackageProblemTypes;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExecutionServiceImpl extends SuperServiceImpl implements ExecutionService {

    @Override
    public void importFile(File file, Map<String, Object> params) {
        Unmarshaller unmarshaller = getUnmarshaller(Resultsexecution.class);
        if (unmarshaller == null) {
            logError("Не удалось создать демаршаллизатор");
            return;
        }

        Resultsexecution resultsExecution = null;
        try {
            resultsExecution = (Resultsexecution) unmarshaller.unmarshal(file);
            processResultExecution(resultsExecution, params);
        } catch (Exception e) {
            logError(e);
        }
    }


    private void processResultExecution(Resultsexecution resultsExecution, Map<String, Object> params) {
        List<WorkPackageProblem> problems = new ArrayList<>();
        List<Resultsexecution.RegProject.Results.Result.Risks> riskList = getRisks(resultsExecution);
        for (Resultsexecution.RegProject.Results.Result.Risks risks : riskList) {
            Resultsexecution.RegProject.Results.Result.Risks.Risk risk = risks.getRisk();
            WorkPackageProblem problem = parseRisk(risk, params);
            problems.add(problem);
        }


    }

    private List<Resultsexecution.RegProject.Results.Result.Risks> getRisks(Resultsexecution resultsExecution) {
        List<Resultsexecution.RegProject.Results.Result.Risks> riskList = null;
        Resultsexecution.RegProject regProject = resultsExecution.getRegProject();
        if (regProject != null) {
            Resultsexecution.RegProject.Results results = regProject.getResults();
            if (results != null) {
                List<Resultsexecution.RegProject.Results.Result> resultList = results.getResult();
                if (resultList != null && !resultList.isEmpty()) {
                    Resultsexecution.RegProject.Results.Result result = resultList.get(0);
                    riskList = result.getRisks();
                }
            }
        }

        return riskList;
    }

    private WorkPackageProblem parseRisk(Resultsexecution.RegProject.Results.Result.Risks.Risk risk, Map<String, Object> params) {

        Long workPackageId = (Long) params.get("workPackageId");
        Long authorId = (Long) params.get("authorId");
        WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);
        Long projectId = (workPackage == null) ? null : workPackage.getProjectId();

        WorkPackageProblem workPackageProblem = WorkPackageProblem.builder()
                                                .projectId(projectId)
                                                .workPackageId(workPackageId)
                                                .userCreatorId(authorId)
                                                .description(risk.getName() + "\n" + risk.getReason())
                                                .status(WorkPackageProblemStatuses.CREATED.getValue())
                                                .type(WorkPackageProblemTypes.RISK.getValue())
                                                .createdAt(new Timestamp(System.currentTimeMillis()))
                                                .updatedAt(new Timestamp(System.currentTimeMillis()))
                                                .build();
        return workPackageProblem;
    }
}
