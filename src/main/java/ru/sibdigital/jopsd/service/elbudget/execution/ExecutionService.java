package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

import java.io.InputStream;
import java.util.Map;

public interface ExecutionService {
    void importFile(InputStream inputStream, Map<String, Object> params) throws Exception;

    Resultsexecution getResultsexecutionInInputStream(InputStream inputStream);

    Long getResultMetaIdFromResultsexecution(Resultsexecution resultsexecution);

    Long getRegProjectMetaIdFromResultsexecution(Resultsexecution resultsexecution);

    WorkPackage findWorkPackage(InputStream inputStream) throws Exception;

    WorkPackage putMetaIdToWorkPackage(InputStream inputStream, Long workPackageId) throws Exception;

    WorkPackage createWorkPackage(InputStream inputStream, String workPackageName, Long projectId, String projectName, Long authorId, Long organizationId) throws Exception;

    void matchData(InputStream inputStream, Map<String, Object> params) throws Exception;
}
