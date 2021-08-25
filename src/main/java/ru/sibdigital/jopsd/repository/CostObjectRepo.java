package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.CostObject;

import java.util.Optional;

@Repository
public interface CostObjectRepo extends JpaRepository<CostObject, Long> {
    Optional<CostObject> findCostObjectByMetaId(Long metaId);
}
