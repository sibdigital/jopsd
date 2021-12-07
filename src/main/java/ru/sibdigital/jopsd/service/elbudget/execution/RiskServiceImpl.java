package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.config.user.details.CustomUserDetails;
import ru.sibdigital.jopsd.dto.elbudget.execution.EbRisk;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.enums.TargetProbability;
import ru.sibdigital.jopsd.model.enums.WorkPackageProblemStatuses;
import ru.sibdigital.jopsd.model.enums.WorkPackageProblemTypes;
import ru.sibdigital.jopsd.model.opsd.*;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RiskServiceImpl extends SuperServiceImpl implements RiskService {

    @Override
    public void saveRisks(InputStream inputStream, Map<String, Object> params) {
        try {
            Resultsexecution resultsExecution = executionParseService.unmarshalInputStream(inputStream);
            Long workPackageId = (Long) params.get("workPackageId");
            WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);

            List<? extends EbRisk> wpEbRisks = executionParseService.getResultRiskList(resultsExecution);
            saveEbRisksForWorkPackages(wpEbRisks, workPackage);

            Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator rpResultIndicator = executionParseService.getRpResultIndicator(resultsExecution);
            targetService.saveRpResultIndicator(rpResultIndicator, workPackage);

        } catch (JAXBException jaxbException) {
            log.error("Не удалось распарсить файл. {}", jaxbException);
        }
    }

    @Override
    public void saveRisks(Resultsexecution.RegProject regProject, Map<String, Object> params) {
        List<WorkPackageProblem> problems = new ArrayList<>();
        List<Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks> riskList = getRisks(regProject);
        if (riskList != null) {
            for (Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks risks : riskList) {
                EbRisk risk = risks.getRisk();
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

    private WorkPackageProblem parseRisk(EbRisk risk, Map<String, Object> params) {
        Long workPackageId = (Long) params.get("workPackageId");
        Long authorId = (Long) params.get("authorId");
        WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);
        User author = userRepository.findById(authorId).orElse(null);

        WorkPackageProblem workPackageProblem = WorkPackageProblem.builder()
                .project(workPackage.getProject())
                .workPackage(workPackage)
                .userCreator(author)
                .description(risk.getName() + "\n" + risk.getReason())
                .status(WorkPackageProblemStatuses.CREATED.getValue())
                .type(WorkPackageProblemTypes.RISK.getValue())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();
        return workPackageProblem;
    }

    @Override
    public void saveEbRisksForWorkPackages(List<? extends EbRisk> ebRisks, WorkPackage workPackage) {
        List<Risk> riskList = new ArrayList<>();
        List<WorkPackageProblem> workPackageProblemList = new ArrayList<>();
        ebRisks.forEach(ebRisk -> {
            Risk risk = riskRepo.findRiskByProjectAndName(workPackage.getProject(), ebRisk.getName());

            if (risk == null) {
                risk = createRiskByEbRisk(ebRisk, workPackage.getProject());
                WorkPackageProblem workPackageProblem = createWorkPackageProblemByEbRisk(workPackage, risk, ebRisk);
                workPackageProblemList.add(workPackageProblem);
            } else {
                risk = changeRiskByEbRisk(risk, ebRisk);
                WorkPackageProblem workPackageProblem = workPackageProblemRepo.findByWorkPackageAndRisk(workPackage, risk);
                if (workPackageProblem == null) {
                    workPackageProblem = createWorkPackageProblemByEbRisk(workPackage, risk, ebRisk);
                } else {
                    workPackageProblem = changeWorkPackageProblemByEbRisk(workPackageProblem, ebRisk);
                }
                workPackageProblemList.add(workPackageProblem);
            }
            riskList.add(risk);
        });

        if (!riskList.isEmpty()) {
            riskRepo.saveAll(riskList);
            workPackageProblemRepo.saveAll(workPackageProblemList);
        }
    }

    @Override
    public void saveEbRisksForTargets(List<? extends EbRisk> ebRisks, Target target) {
        List<Risk> riskList = new ArrayList<>();
        List<TargetRisk> targetRiskList = new ArrayList<>();
        ebRisks.forEach(ebRisk -> {
            Risk risk = riskRepo.findRiskByProjectAndName(target.getProject(), ebRisk.getName());

            if (risk == null) {
                risk = createRiskByEbRisk(ebRisk, target.getProject());
                TargetRisk targetRisk = createTargetRiskByEbRisk(target, risk, ebRisk);
                targetRiskList.add(targetRisk);
            } else {
                risk = changeRiskByEbRisk(risk, ebRisk);
                TargetRisk targetRisk = targetRiskRepository.findByTargetAndRisk(target, risk);
                if (targetRisk == null) {
                    targetRisk = createTargetRiskByEbRisk(target, risk, ebRisk);
                } else {
                    targetRisk = changeTargetRiskByEbRisk(targetRisk, ebRisk);
                }
                targetRiskList.add(targetRisk);
            }
            riskList.add(risk);
        });

        if (!riskList.isEmpty()) {
            riskRepo.saveAll(riskList);
            targetRiskRepository.saveAll(targetRiskList);
        }
    }

    private Risk createRiskByEbRisk(EbRisk ebRisk, Project project) {
        Integer riskProbability = (ebRisk.getRiskProbability() == null || ebRisk.getRiskProbability().equals("")) ? 50 : Integer.valueOf(ebRisk.getRiskProbability());
        Integer typeProbabilityPosition = TargetProbability.getTargetProbabilityByProbabilityValue(riskProbability).getValue();
        Enumeration possibility = enumerationRepo.findEnumerationByTypeAndPositionAndActive("Possibility", typeProbabilityPosition, true);
        String solution = "";
        if (!ebRisk.getProposedSolutions().isEmpty() && ebRisk.getProposedSolutions().get(0).getProposedSolution() != null) {
            solution = ebRisk.getProposedSolutions().get(0).getProposedSolution().getDescription();
        }

        Risk risk = Risk.builder()
                .name(ebRisk.getName())
                .description(ebRisk.getReason())
                .project(project)
                .type("ProjectRisk")
                .probabilityValue(riskProbability)
                .possibility(possibility)
                .solution(solution)
                .isApprove(true)
                .createdAt(Timestamp.from(Instant.now()))
                .updatedAt(Timestamp.from(Instant.now()))
                .build();
        return risk;
    }

    private TargetRisk createTargetRiskByEbRisk(Target target, Risk risk, EbRisk ebRisk) {
        XMLGregorianCalendar xgc =
                (!ebRisk.getProposedSolutions().isEmpty() && (ebRisk.getProposedSolutions().get(0).getProposedSolution() != null)) ?
                        ebRisk.getProposedSolutions().get(0).getProposedSolution().getExecutionPeriod() : null;
        LocalDate solutionPeriod = xgc != null ? xgc.toGregorianCalendar().toZonedDateTime().toLocalDate() : null;
        TargetRisk targetRisk = TargetRisk.builder()
                .target(target)
                .risk(risk)
                .isSolved(ebRisk.isSolved())
                .solutionDate(solutionPeriod)
                .build();

        return targetRisk;
    }

    private WorkPackageProblem createWorkPackageProblemByEbRisk(WorkPackage workPackage, Risk risk, EbRisk ebRisk) {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = currentUser.getUser();

        XMLGregorianCalendar xgc =
                (!ebRisk.getProposedSolutions().isEmpty() && (ebRisk.getProposedSolutions().get(0).getProposedSolution() != null)) ?
                        ebRisk.getProposedSolutions().get(0).getProposedSolution().getExecutionPeriod() : null;
        LocalDateTime solutionPeriod = xgc.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
        WorkPackageProblem workPackageProblem = WorkPackageProblem.builder()
                                            .workPackage(workPackage)
                                            .risk(risk)
                                            .userCreator(user)
                                            .description(ebRisk.getReason())
                                            .status("created")
                                            .type("risk")
                                            .solutionDate(solutionPeriod)
                                            .createdAt(Timestamp.from(Instant.now()))
                                            .updatedAt(Timestamp.from(Instant.now()))
                                            .build();

        return workPackageProblem;
    }

    private Risk changeRiskByEbRisk(Risk risk, EbRisk ebRisk) {
        Integer riskProbability = (ebRisk.getRiskProbability() == null || ebRisk.getRiskProbability().equals("")) ? 50 : Integer.valueOf(ebRisk.getRiskProbability());
        String solution = "";
        if (!ebRisk.getProposedSolutions().isEmpty() &&  ebRisk.getProposedSolutions().get(0).getProposedSolution() != null) {
            solution = ebRisk.getProposedSolutions().get(0).getProposedSolution().getDescription();
        }
        risk.setProbabilityValue(riskProbability);
        if (solution != null) {
            risk.setSolution(solution);
        }
        return risk;
    }

    private TargetRisk changeTargetRiskByEbRisk(TargetRisk targetRisk, EbRisk ebRisk) {
        XMLGregorianCalendar xgc =
                (!ebRisk.getProposedSolutions().isEmpty() && (ebRisk.getProposedSolutions().get(0).getProposedSolution() != null)) ?
                        ebRisk.getProposedSolutions().get(0).getProposedSolution().getExecutionPeriod() : null;
        LocalDate solutionPeriod = xgc != null ? xgc.toGregorianCalendar().toZonedDateTime().toLocalDate() : null;

        targetRisk.setSolutionDate(solutionPeriod);
        targetRisk.setSolved(ebRisk.isSolved());
        return targetRisk;
    }

    private WorkPackageProblem changeWorkPackageProblemByEbRisk(WorkPackageProblem workPackageProblem, EbRisk ebRisk) {
        XMLGregorianCalendar xgc =
                (!ebRisk.getProposedSolutions().isEmpty() && (ebRisk.getProposedSolutions().get(0).getProposedSolution() != null)) ?
                        ebRisk.getProposedSolutions().get(0).getProposedSolution().getExecutionPeriod() : null;
        LocalDateTime solutionPeriod = xgc != null ? xgc.toGregorianCalendar().toZonedDateTime().toLocalDateTime() : null;

        workPackageProblem.setSolutionDate(solutionPeriod);
        workPackageProblem.setStatus(ebRisk.isSolved() ? "solved" : "created");
        return workPackageProblem;
    }
}
