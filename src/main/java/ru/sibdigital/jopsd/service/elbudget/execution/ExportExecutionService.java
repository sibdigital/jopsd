package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.model.opsd.WorkPackage;

public interface ExportExecutionService {

    Boolean readyForExportEb(Long idWorkPackage);

    byte[] createExecutionFromWP(WorkPackage workPackage);
}
