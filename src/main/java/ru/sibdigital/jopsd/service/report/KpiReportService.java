package ru.sibdigital.jopsd.service.report;

public interface KpiReportService {
    byte[] exportKpiReport(Long userId);
    byte[] exportKpiReportProject(Long projectId);
}
