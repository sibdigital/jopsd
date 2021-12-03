package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

import java.io.InputStream;

public interface ExecutionService {

    Resultsexecution getResultsexecutionInInputStream(InputStream inputStream);

    Long getResultMetaIdFromResultsexecution(Resultsexecution resultsexecution);

    Long getRegProjectMetaIdFromResultsexecution(Resultsexecution resultsexecution);

    WorkPackage findWorkPackage(InputStream inputStream) throws Exception;

    WorkPackage putMetaIdToWorkPackage(Resultsexecution resultsexecution, Long workPackageId) throws Exception;

    WorkPackage createWorkPackage(Resultsexecution resultsexecution, String workPackageName, Long projectId, String projectName, Long authorId, Long organizationId) throws Exception;

}
