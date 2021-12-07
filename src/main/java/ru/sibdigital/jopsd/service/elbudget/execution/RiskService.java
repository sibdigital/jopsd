package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.dto.elbudget.execution.EbRisk;
import ru.sibdigital.jopsd.model.opsd.Target;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface RiskService {
    void saveRisks(Resultsexecution.RegProject regProject, Map<String, Object> params);
    void saveRisks(InputStream inputStream, Map<String, Object> params);
    void saveEbRisksForWorkPackages(List<? extends EbRisk> ebRisks, WorkPackage workPackage);
    void saveEbRisksForTargets(List<? extends EbRisk> ebRisks, Target target);
}
