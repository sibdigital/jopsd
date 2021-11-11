package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.dto.KpiDto;

public interface KpiService {
    Object execute(KpiDto kpiDto);
    KpiDto save(KpiDto kpiDto);
}
