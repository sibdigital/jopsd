package ru.sibdigital.jopsd.service.report;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface JasperReportService {

    <T> byte[] exportJasperReport(String jrxmlPath, List<T> dataSourceList, Map<String, Object> parameters, String reportFormat);
    String getQueryString(String path) throws IOException;
}
