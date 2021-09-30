package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.model.opsd.WorkPackage;

public interface WorkPackageService {
    WorkPackage putMetaId(Long workPackageId, Long metaId);
}
