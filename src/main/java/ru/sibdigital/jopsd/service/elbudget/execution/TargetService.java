package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.TargetMatch;
import ru.sibdigital.jopsd.model.opsd.Target;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface TargetService {
    List<TargetMatch> matchTargets(InputStream inputStream) throws Exception;
    List<TargetMatch> createAndSaveTargetValues(List<TargetMatch> targetMatches, Map<String, Object> params);
    Target changeMetaId(Long targetId, Long metaId);
}
