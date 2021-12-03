package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.model.opsd.WorkPackage;

import java.util.List;

public interface WorkPackageService {
    WorkPackage putMetaId(Long workPackageId, Long metaId);
    List<WorkPackage> getWorkPackagesByProject(Long projectId);
    WorkPackage getWorkPackageById(Long workPackageId);
}
