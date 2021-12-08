package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.KpiDto;
import ru.sibdigital.jopsd.model.opsd.Kpi;
import ru.sibdigital.jopsd.model.opsd.KpiVariable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Slf4j
public class KpiServiceImpl extends SuperServiceImpl implements KpiService {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Object execute(KpiDto kpiDto) {
        Query query = entityManager.createNativeQuery(kpiDto.getKpi().getQuery());
        kpiDto.getKpiVariables().stream()
                .filter(kpiVariable -> kpiVariable.getName() != null)
                .forEach(kpiVariable -> {
                    query.setParameter(kpiVariable.getName(), Integer.valueOf(kpiVariable.getValue()));
        });
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
