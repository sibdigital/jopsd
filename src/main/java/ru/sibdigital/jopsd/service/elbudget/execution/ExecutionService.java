package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.model.WorkPackage;

import java.io.InputStream;
import java.util.Map;

public interface ExecutionService {
    void importFile(InputStream inputStream, Map<String, Object> params) throws Exception;
}
