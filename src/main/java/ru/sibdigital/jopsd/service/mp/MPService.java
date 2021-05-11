package ru.sibdigital.jopsd.service.mp;

import net.sf.mpxj.MPXJException;

import java.io.InputStream;
import java.util.Map;

public interface MPService {
    void importFile(InputStream inputStream, Map<String, Object> params) throws MPXJException;
}
