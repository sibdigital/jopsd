package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.KpiDto;
import ru.sibdigital.jopsd.model.opsd.Kpi;
import ru.sibdigital.jopsd.model.opsd.KpiVariable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Slf4j
public class KpiServiceImpl extends SuperServiceImpl implements KpiService {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Object execute(KpiDto kpiDto) {
        String queryString = kpiDto.getKpi().getQuery().toLowerCase();
        queryString = queryString.replaceAll(queryString,"delete");
        queryString = queryString.replaceAll(queryString,"drop");
        NativeQueryImpl query = (NativeQueryImpl) entityManager.createNativeQuery(queryString);
        kpiDto.getKpiVariables().stream()
                .filter(kpiVariable -> kpiVariable.getName() != null)
                .forEach(kpiVariable -> {
                    query.setParameter(kpiVariable.getName(), Integer.valueOf(kpiVariable.getValue()));
        });
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.getResultList();
    }

    @Override
    public KpiDto save(KpiDto kpiDto) {
        Kpi kpi = kpiRepository.save(kpiDto.getKpi());
        List<KpiVariable> kpiVariables = kpiDto.getKpiVariables();
        kpiVariables.forEach(kpiVariable -> {kpiVariable.setKpi(kpi);});
        kpiVariableRepository.saveAll(kpiVariables);
        return KpiDto.builder().kpi(kpi).kpiVariables(kpiVariables).build();
    }
}
