package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

import java.util.List;

@Service
@Slf4j
public class WorkPackageServiceImpl extends SuperServiceImpl implements WorkPackageService{
    @Override
    public WorkPackage putMetaId(Long workPackageId, Long metaId) {
        WorkPackage workPackage = workPackageRepo.findById(workPackageId).orElse(null);
        if (workPackage != null) {
            workPackage.setMetaId(metaId);
            workPackageRepo.save(workPackage);
        }
        return workPackage;
    }

    @Override
    public List<WorkPackage> getWorkPackagesByProject(Long projectId) {
        return workPackageRepo.findAllByProjectId(projectId);
    }

    @Override
    public WorkPackage getWorkPackageById(Long workPackageId) {
        return workPackageRepo.findById(workPackageId).orElse(null);
    }
}
