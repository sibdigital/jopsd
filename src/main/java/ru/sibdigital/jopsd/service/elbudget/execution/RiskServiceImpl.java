package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.model.WorkPackageProblem;
import ru.sibdigital.jopsd.model.enums.WorkPackageProblemStatuses;
import ru.sibdigital.jopsd.model.enums.WorkPackageProblemTypes;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RiskServiceImpl extends SuperServiceImpl implements RiskService {

    @Override
    public void saveRisks(Resultsexecution.RegProject regProject, Map<String, Object> params) {
        List<WorkPackageProblem> problems = new ArrayList<>();
        List<Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks> riskList = getRisks(regProject);
        if (riskList != null) {
            for (Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks risks : riskList) {
                Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks.Risk risk = risks.getRisk();
                WorkPackageProblem problem = parseRisk(risk, params);
                problems.add(problem);
            }

//        workPackageProblemRepo.saveAll(problems);
        }
    }

    private List<Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks> getRisks(Resultsexecution.RegProject regProject) {
        List<Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks> riskList = null;
        Resultsexecution.RegProject.Results results = regProject.getResults();
        if (results != null) {
            List<Resultsexecution.RegProject.Results.Result> resultList = results.getResult();
            if (resultList != null && !resultList.isEmpty()) {
                Resultsexecution.RegProject.Results.Result result = resultList.get(0);
                Resultsexecution.RegProject.Results.Result.RpResultIndicators rpResultIndicators = result.getRpResultIndicators();
                if (rpResultIndicators != null) {
                    List<Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator> rpResultIndicatorList = rpResultIndicators.getRpResultIndicator();
                    Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator rpResultIndicator = rpResultIndicatorList.get(0);
                    riskList = rpResultIndicator.getRisks();
                }
            }
        }

        return riskList;
    }

    private WorkPackageProblem parseRisk(Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks.Risk risk, Map<String, Object> params) {
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
