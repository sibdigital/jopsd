package ru.sibdigital.jopsd.service.elbudget;

import java.io.File;
import java.util.Map;

public interface ExecutionService {
    void importFile(File file, Map<String, Object> params);
}
