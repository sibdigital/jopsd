package ru.sibdigital.jopsd.service.elbudget.execution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExecutionParseServiceImpl extends SuperServiceImpl implements ExecutionParseService {

    @Override
    public Resultsexecution unmarshalInputStream(InputStream inputStream) throws JAXBException {
        Unmarshaller unmarshaller = getUnmarshaller(Resultsexecution.class);
        if (unmarshaller == null) {
            throw new JAXBException("Не удалось создать демаршаллизатор");
        }

        Resultsexecution resultsExecution = (Resultsexecution) unmarshaller.unmarshal(inputStream);
        return  resultsExecution;
    }

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
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> purposeCriteriaList = new ArrayList<>();
        Resultsexecution.RegProject.PurposeCriterias criterias = regProject.getPurposeCriterias();
        if (criterias != null) {
            purposeCriteriaList = criterias.getPurposeCriteria();
        }

        return purposeCriteriaList;
    }

    @Override
    public List<Resultsexecution.RegProject.Results.Result.Risks.Risk> getResultRiskList(Resultsexecution resultsexecution) {
        List<Resultsexecution.RegProject.Results.Result> result = resultsexecution.getRegProject().getResults().getResult();
        if (!result.isEmpty()) {
            return result.get(0).getRisks().stream()
                    .map(item -> item.getRisk())
                    .filter(item -> item != null)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Override
    public Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator getRpResultIndicator(Resultsexecution resultsexecution) {
        Resultsexecution.RegProject.Results.Result result = getResult(resultsexecution);
        List<Resultsexecution.RegProject.Results.Result.RpResultIndicators.RpResultIndicator> rpResultIndicatorList = result.getRpResultIndicators().getRpResultIndicator();
        if (!rpResultIndicatorList.isEmpty()) {
            return rpResultIndicatorList.get(0);
        } else {
            return null;
        }
    }
}
