package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.CustomActionsRole;

@Repository
public interface CustomActionsRoleRepository extends JpaRepository<CustomActionsRole, Long>, JpaSpecificationExecutor<CustomActionsRole> {
}