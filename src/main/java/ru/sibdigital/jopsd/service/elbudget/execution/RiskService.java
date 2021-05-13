package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;

import java.util.Map;

public interface RiskService {
    void saveRisks(Resultsexecution.RegProject regProject, Map<String, Object> params);
}
