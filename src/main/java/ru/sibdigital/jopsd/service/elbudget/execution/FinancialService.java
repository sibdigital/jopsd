package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.model.opsd.CostObject;

import java.io.InputStream;
import java.util.Map;

public interface FinancialService {
    CostObject saveFinances(InputStream inputStream, Map<String, Object> params) throws Exception;
//    void saveFinances(Resultsexecution.RegProject regProject, WorkPackage workPackage, Map<String, Object> params);
}
