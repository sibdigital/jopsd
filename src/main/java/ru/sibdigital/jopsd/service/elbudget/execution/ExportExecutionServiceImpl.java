package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.*;
import ru.sibdigital.jopsd.model.opsd.*;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

@Service
@Slf4j
public class ExportExecutionServiceImpl extends SuperServiceImpl implements ExportExecutionService {

    @Autowired
    ObjectFactory objectFactory;


    @Override
    public Boolean readyForExportEb(Long idWorkPackage) {
        Calendar calendar = Calendar.getInstance();
        Integer year = calendar.get(Calendar.YEAR);
        WorkPackage workPackage = workPackageRepo.getWorkPackageReadyForEB(idWorkPackage, year);
        return (workPackage != null);
    }

    @Override
    public byte[] createExecutionFromWP(WorkPackage workPackage) {
        try {

            Resultsexecution resultsexecution = objectFactory.createResultsexecution();
            resultsexecution.setRegProject(getRegProjectForExecution(workPackage));

            Marshaller marshaller = getMarshaller(Resultsexecution.class);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            marshaller.marshal(resultsexecution, outputStream);
            byte[] bytes = outputStream.toByteArray();
            return bytes;
        }
        catch (JAXBException jaxbException) {
            log.error("Ошибка сериализации JAXB. {}", jaxbException.getMessage());
            return null;
        }
    }

    private Resultsexecution.RegProject getRegProjectForExecution(WorkPackage workPackage) {
        Resultsexecution.RegProject regProject = objectFactory.createResultsexecutionRegProject();

        Project project = workPackage.getProject();

        regProject.setRegProjectMetaId(project.getMetaId());
        regProject.setYear(getYear(workPackage));
        regProject.setMonth(getCurrentMonth());
        regProject.setResults(getResults(workPackage));
        regProject.setPurposeCriterias(getPurposeCriterias(workPackage));

        return regProject;
    }

    private Resultsexecution.RegProject.Results getResults(WorkPackage workPackage) {
        Resultsexecution.RegProject.Results results = objectFactory.createResultsexecutionRegProjectResults();
        Resultsexecution.RegProject.Results.Result result = objectFactory.createResultsexecutionRegProjectResultsResult();
        result.setResultMetaId(workPackage.getMetaId());

        result.setRpResultIndicators(getRpResultIndicators(workPackage));
        results.getResult().add(result);

        return results;
    }

    private String getYear(WorkPackage workPackage) {
        LocalDateTime dateTime = workPackage.getStartDate();
        Integer year = (dateTime != null) ? Calendar.getInstance().get(Calendar.YEAR) - dateTime.getYear(): null;

        return (year != null) ? year.toString() : null;
    }

    private String getCurrentMonth() {
        final Integer currentMonth = Calendar.getInstance().get(Calendar.MONTH) - 1;
        return currentMonth.toString();
    }

    private Resultsexecution.RegProject.Results.Result.RpResultIndicators getRpResultIndicators(WorkPackage workPackage){
        Resultsexecution.RegProject.Results.Result.RpResultIndicators rpResultIndicators = objectFactory.createResultsexecutionRegProjectResultsResultRpResultIndicators();
        Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator rpResultIndicator = objectFactory.createResultsexecutionRegProjectResultsResultRpResultIndicatorsRpResultIndicator();

        WorkPackageTarget workPackageTarget = workPackageTargetRepo.findWorkPackageTargetAsRpResultIndicator(workPackage.getId(), Calendar.getInstance().get(Calendar.YEAR));
        if (workPackageTarget != null) {
            Target target = workPackageTarget.getTarget();
            rpResultIndicator.setResultIndicatorMetaId(target.getMetaId());
            rpResultIndicator.setValuePrognosInEndOfYear(workPackageTarget.getPlanValue());
//            rpResultIndicator.setValueFact(workPackageTarget.getValue());
            rpResultIndicator.setValueFact(BigDecimal.ZERO);
            rpResultIndicator.setRisks(getRpResultIndicatorsRisks(target));
            try {
                if (target.getResultDueDate() != null) {
                    rpResultIndicator.setExecutionDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(target.getResultDueDate().toLocalDate().toString()));
                }
            } catch (DatatypeConfigurationException datatypeConfigurationException) {
                log.error("Не удалось преобразовать LocalDate в XMLGregorianCalendar. {}", datatypeConfigurationException);
            }
        }
        rpResultIndicators.setRpResultIndicator(List.of(rpResultIndicator));
        return rpResultIndicators;
    }

    private Resultsexecution.RegProject.PurposeCriterias getPurposeCriterias(WorkPackage workPackage) {
        Resultsexecution.RegProject.PurposeCriterias purposeCriterias = objectFactory.createResultsexecutionRegProjectPurposeCriterias();
        List<Target> targetList = targetService.getTargetsByWorkPackage(workPackage);
        List<Target> targetListWMetaId = targetList.stream().filter(item -> item.getMetaId() != null).collect(Collectors.toList());
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> purposeCriteriaList = targetListWMetaId.stream()
                .map(target -> getPurposeCriteriaByTarget(workPackage, target))
                .collect(Collectors.toList());
        purposeCriterias.setPurposeCriteria(purposeCriteriaList);
        return purposeCriterias;
    }

    private Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria getPurposeCriteriaByTarget(WorkPackage workPackage, Target target) {
        Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria purposeCriteria = objectFactory.createResultsexecutionRegProjectPurposeCriteriasPurposeCriteria();
        purposeCriteria.setPurposeCriteriaMetaId(target.getMetaId());
        purposeCriteria.setDescription(target.getComment());
        purposeCriteria.setExecutionConfirmingDocuments(null);
        purposeCriteria.setPurposeCriteriaMonthlyExecutions(getPurposeCriteriaMonthlyExecutions(workPackage, target));
        purposeCriteria.setRisks(getPurposeCriteriaRisks(target));
        return purposeCriteria;
    }

    private Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions getPurposeCriteriaMonthlyExecutions(WorkPackage workPackage, Target target) {
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        List<WorkPackageTarget> wpTargets = workPackageTargetRepo.findAllByWorkPackageIdAndTargetIdAndYear(workPackage.getId(), target.getId(), year);

        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution> purposeCriteriaMonthlyExecutionList = new ArrayList<>();
        wpTargets.forEach(
            wpTarget -> {
                Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions.PurposeCriteriaMonthlyExecution purposeCriteriaMonthlyExecution =
                        objectFactory.createResultsexecutionRegProjectPurposeCriteriasPurposeCriteriaPurposeCriteriaMonthlyExecutionsPurposeCriteriaMonthlyExecution();
                Integer month = wpTarget.getMonth() - 1;
                purposeCriteriaMonthlyExecution.setMonth(month.toString());
                purposeCriteriaMonthlyExecution.setFactPrognos(wpTarget.getValue());
                purposeCriteriaMonthlyExecution.setTypeFact("0");
                purposeCriteriaMonthlyExecutionList.add(purposeCriteriaMonthlyExecution);
            }
        );

        Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.PurposeCriteriaMonthlyExecutions purposeCriteriaMonthlyExecutions = objectFactory.createResultsexecutionRegProjectPurposeCriteriasPurposeCriteriaPurposeCriteriaMonthlyExecutions();
        purposeCriteriaMonthlyExecutions.setPurposeCriteriaMonthlyExecution(purposeCriteriaMonthlyExecutionList);
        return purposeCriteriaMonthlyExecutions;
    }

    private List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.Risks> getPurposeCriteriaRisks(Target target) {

        List<TargetRisk> targetRisks = targetRiskRepository.findAllByTarget(target);
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.Risks> ebRisksList = targetRisks.stream()
                .map(targetRisk ->  {
                    EbRisk ebRisk =  createEbRisk(targetRisk);
                    Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria.Risks risks = objectFactory.createResultsexecutionRegProjectPurposeCriteriasPurposeCriteriaRisks();
                    risks.setRisk(ebRisk);
                    return risks;
                })
                .collect(Collectors.toList());
        return ebRisksList;
    }

    private List<Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks> getRpResultIndicatorsRisks(Target target) {

        List<TargetRisk> targetRisks = targetRiskRepository.findAllByTarget(target);
        List<Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks> ebRisksList = targetRisks.stream()
                .map(targetRisk ->  {
                    EbRisk ebRisk =  createEbRisk(targetRisk);
                    Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator.Risks risks = objectFactory.createResultsexecutionRegProjectResultsResultRpResultIndicatorsRpResultIndicatorRisks();
                    risks.setRisk(ebRisk);
                    return risks;
                })
                .collect(Collectors.toList());
        return ebRisksList;
    }

    private EbRisk createEbRisk(TargetRisk targetRisk) {
        Risk risk = targetRisk.getRisk();

        XMLGregorianCalendar solutionDate = null;
        XMLGregorianCalendar expectedDate = null;
        try {
            solutionDate = (targetRisk.getSolutionDate() != null) ?
                            DatatypeFactory.newInstance().newXMLGregorianCalendar(targetRisk.getSolutionDate().toString()) :
                            DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.now().with(lastDayOfYear()).toString());

            expectedDate = (risk.getExpectedDate() != null) ?
                            DatatypeFactory.newInstance().newXMLGregorianCalendar(risk.getExpectedDate().toString()) :
                            DatatypeFactory.newInstance().newXMLGregorianCalendar(LocalDate.now().with(lastDayOfYear()).toString());
        } catch (DatatypeConfigurationException datatypeConfigurationException) {
            log.error("Ошибка конвертации в формат xmlGregorianCalendar. {}", datatypeConfigurationException);
        }
        EbProposedSolutions ebProposedSolutions = new EbProposedSolutions();
        EbProposedSolution ebProposedSolution = EbProposedSolution.builder()
                .executionPeriod(solutionDate)
                .isCompleted(false)
                .description((risk.getSolution() != null) ? risk.getSolution() : " ")
                .comment(null)
                .build();
        ebProposedSolutions.setProposedSolution(ebProposedSolution);

        EbRisk ebRisk = EbRisk.builder()
                        .name(risk.getName())
                        .isSolved(targetRisk.getSolved())
                        .riskProbability(risk.getProbabilityValue().toString())
                        .reason(risk.getDescription())
                        .temporary(true)
                        .monetary(false)
                        .fact(false)
                        .consequenceSum(null)
                        .effect(null)
                        .powerDecision("0")
                        .proposedSolutions(List.of(ebProposedSolutions))
                        .expectedDate(expectedDate)
                        .build();

        return ebRisk;
    }
}
