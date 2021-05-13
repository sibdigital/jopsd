package ru.sibdigital.jopsd.service.mp;

import net.sf.mpxj.MPXJException;
import ru.sibdigital.jopsd.model.WorkPackage;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface MPService {
    List<WorkPackage> importFile(InputStream inputStream, Map<String, Object> params) throws MPXJException;
}
