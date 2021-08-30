package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.RiskCharact;

@Repository
public interface RiskCharactRepository extends JpaRepository<RiskCharact, Long>, JpaSpecificationExecutor<RiskCharact> {
}