package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.ControlLevelRole;

@Repository
public interface ControlLevelRoleRepository extends JpaRepository<ControlLevelRole, Long>, JpaSpecificationExecutor<ControlLevelRole> {
}