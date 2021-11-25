package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.KpiVariable;

import java.util.List;

@RepositoryRestResource
public interface KpiVariableRepository extends JpaRepository<KpiVariable, Long>, JpaSpecificationExecutor<KpiVariable> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(KpiVariable kpiVariable);

    List<KpiVariable> findAllByKpi_Id(Long kpiId);
}
