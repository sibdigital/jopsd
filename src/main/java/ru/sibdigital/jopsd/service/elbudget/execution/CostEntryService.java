package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;

import java.util.Map;

public interface CostEntryService {
    void saveCostObjects(Resultsexecution.RegProject regProject, Map<String, Object> params);
}
