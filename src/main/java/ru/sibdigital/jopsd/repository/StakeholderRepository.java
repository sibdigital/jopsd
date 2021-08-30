package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.Stakeholder;

@Repository
public interface StakeholderRepository extends JpaRepository<Stakeholder, Long>, JpaSpecificationExecutor<Stakeholder> {
}