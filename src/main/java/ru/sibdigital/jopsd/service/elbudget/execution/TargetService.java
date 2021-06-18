package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.TargetMatch;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.WorkPackage;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface TargetService {
    List<TargetMatch> matchTargets(InputStream inputStream) throws Exception;
    void saveTargets(Resultsexecution.RegProject regProject, WorkPackage workPackage, Map<String, Object> params);
    void createAndSaveTargetValues(List<TargetMatch> targetMatches, Map<String, Object> params);
}
