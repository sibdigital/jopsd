package ru.sibdigital.jopsd.service.elbudget.execution;

import org.springframework.web.bind.annotation.RequestParam;
import ru.sibdigital.jopsd.model.WorkPackage;

import java.io.InputStream;
import java.util.Map;

public interface ExecutionService {
    void importFile(InputStream inputStream, Map<String, Object> params) throws Exception;

    Long getResultMetaIdInInputStream(InputStream inputStream) throws Exception;

    WorkPackage findWorkPackage(InputStream inputStream) throws Exception;

    WorkPackage createWorkPackage(InputStream inputStream, String workPackageName, Long projectId, String projectName, Long authorId) throws Exception;

    void matchData(InputStream inputStream, Map<String, Object> params) throws Exception;
}
