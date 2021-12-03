package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Risk;
import ru.sibdigital.jopsd.model.opsd.Target;
import ru.sibdigital.jopsd.model.opsd.TargetRisk;

import java.util.List;

@RepositoryRestResource
public interface TargetRiskRepository extends JpaRepository<TargetRisk, Long>, JpaSpecificationExecutor<TargetRisk> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(TargetRisk targetRisk);

    TargetRisk findByTargetAndRisk(Target target, Risk risk);

    List<TargetRisk> findAllByTarget(Target target);
}