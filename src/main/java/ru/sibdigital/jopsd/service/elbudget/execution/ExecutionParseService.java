package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;

import java.io.InputStream;
import java.util.List;

public interface ExecutionParseService {
    Resultsexecution unmarshalInputStream(InputStream inputStream) throws Exception;
    Resultsexecution.RegProject.Results.Result getResult(Resultsexecution.RegProject regProject);
    Resultsexecution.RegProject.Results.Result getResult(Resultsexecution resultsExecution);
    List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> getFinancialSourceList(Resultsexecution.RegProject.Results.Result result);
    List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> getFinancialSourceList(Resultsexecution.RegProject regProject);
    List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> getPurposeCriteriaList(Resultsexecution.RegProject regProject);
}
