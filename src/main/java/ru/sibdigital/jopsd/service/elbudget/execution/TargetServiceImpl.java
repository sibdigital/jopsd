package ru.sibdigital.jopsd.service.elbudget.execution;

import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.util.List;
import java.util.Map;

public class TargetServiceImpl extends SuperServiceImpl implements TargetService {
    @Override
    public void saveTargets(Resultsexecution.RegProject regProject, Map<String, Object> params) {
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> criteriaList = getPurposeCriteriaList(regProject);
        for (Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria criteria : criteriaList) {

        }
    }

    private  List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> getPurposeCriteriaList(Resultsexecution.RegProject regProject) {
        List<Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria> purposeCriteriaList = null;
        Resultsexecution.RegProject.PurposeCriterias criterias = regProject.getPurposeCriterias();
        if (criterias != null) {
            purposeCriteriaList = criterias.getPurposeCriteria();
        }

        return purposeCriteriaList;
    }
}
