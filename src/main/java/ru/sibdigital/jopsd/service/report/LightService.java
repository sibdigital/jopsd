package ru.sibdigital.jopsd.service.report;

public interface LightService {
    byte[] exportLightReport(Long typeId, Long projectId);
}
