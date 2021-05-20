package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.WorkPackage;

import java.util.Map;

public interface FinancialService {
    void saveFinances(Resultsexecution.RegProject regProject, WorkPackage workPackage, Map<String, Object> params);
}
