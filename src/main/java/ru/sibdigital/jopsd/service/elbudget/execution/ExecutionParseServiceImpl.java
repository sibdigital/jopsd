package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.util.List;

@Service
@Slf4j
public class ExecutionParseServiceImpl extends SuperServiceImpl implements ExecutionParseService {

    @Override
    public Resultsexecution.RegProject.Results.Result getResult(Resultsexecution.RegProject regProject) {
        Resultsexecution.RegProject.Results.Result result = null;
        Resultsexecution.RegProject.Results results = regProject.getResults();
        if (results != null) {
            List<Resultsexecution.RegProject.Results.Result> resultList = results.getResult();
            if (resultList != null && !resultList.isEmpty()) {
                result = resultList.get(0);
            }
        }
        return result;
    }

    @Override
    public Resultsexecution.RegProject.Results.Result getResult(Resultsexecution resultsExecution) {
        Resultsexecution.RegProject regProject = resultsExecution.getRegProject();
        Resultsexecution.RegProject.Results.Result result = getResult(regProject);
        return result;
    }

    @Override
    public  List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> getFinancialSourceList(Resultsexecution.RegProject.Results.Result result) {
        List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceList = null;

        Resultsexecution.RegProject.Results.Result.FinancialSources financialSources = result.getFinancialSources();
        if (financialSources != null) {
            financialSourceList = financialSources.getFinancialSource();
        }

        return financialSourceList;
    }

    @Override
    public List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> getFinancialSourceList(Resultsexecution.RegProject regProject) {
        List<Resultsexecution.RegProject.Results.Result.FinancialSources.FinancialSource> financialSourceList = null;
        Resultsexecution.RegProject.Results.Result result = getResult(regProject);
        if (result != null) {
            financialSourceList = getFinancialSourceList(result);
        }
        return financialSourceList;
    }

    @Override
    public List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> getPurposeCriteriaList(Resultsexecution.RegProject regProject) {
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> purposeCriteriaList = null;
        Resultsexecution.RegProject.PurposeCriterias criterias = regProject.getPurposeCriterias();
        if (criterias != null) {
            purposeCriteriaList = criterias.getPurposeCriteria();
        }

        return purposeCriteriaList;
    }
}
