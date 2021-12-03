package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.List;

public interface ExecutionParseService {
    Resultsexecution unmarshalInputStream(InputStream inputStream) throws JAXBException;
    Resultsexecution.RegProject.Results.Result getResult(Resultsexecution.RegProject regProject);
    Resultsexecution.RegProject.Results.Result getResult(Resultsexecution resultsExecution);
    List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> getFinancialSourceList(Resultsexecution.RegProject.Results.Result result);
    List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> getFinancialSourceList(Resultsexecution.RegProject regProject);
    List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> getPurposeCriteriaList(Resultsexecution.RegProject regProject);
    List<Resultsexecution.RegProject.Results.Result.Risks.Risk> getResultRiskList(Resultsexecution resultsexecution);
    Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator getRpResultIndicator(Resultsexecution resultsexecution);
}
