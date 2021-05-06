package ru.sibdigital.jopsd.service.mp;

import net.sf.mpxj.MPXJException;

import java.io.File;
import java.util.Map;

public interface MPService {
    void importFile(File file, Map<String, Object> params) throws MPXJException;
}
