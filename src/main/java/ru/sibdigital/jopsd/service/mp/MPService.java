package ru.sibdigital.jopsd.service.mp;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.Task;
import ru.sibdigital.jopsd.model.WorkPackage;

public interface MPService {
    void importFiles() throws MPXJException;
    WorkPackage createNewWorkPackage(Task task, Long projectId);
}
