package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.CostObject;

import java.util.Optional;

@Repository
public interface CostObjectRepo extends JpaRepository<CostObject, Long>, JpaSpecificationExecutor<CostObject> {
    Optional<CostObject> findCostObjectByMetaId(Long metaId);
}
