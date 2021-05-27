package ru.sibdigital.jopsd.service.elbudget.execution;

import java.io.InputStream;
import java.util.Map;

public interface ExecutionService {
    void importFile(InputStream inputStream, Map<String, Object> params) throws Exception;

    void matchData(InputStream inputStream, Map<String, Object> params) throws Exception;
}
