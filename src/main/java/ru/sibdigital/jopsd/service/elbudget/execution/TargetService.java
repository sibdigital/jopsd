package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;

import java.util.Map;

public interface TargetService {
    void saveTargets(Resultsexecution.RegProject regProject, Map<String, Object> params);
}
