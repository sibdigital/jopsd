package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.CostObject;

import java.util.Optional;

@RepositoryRestResource
public interface CostObjectRepo extends JpaRepository<CostObject, Long>, JpaSpecificationExecutor<CostObject> {
    Optional<CostObject> findCostObjectByMetaId(Long metaId);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(CostObject costObject);
}
