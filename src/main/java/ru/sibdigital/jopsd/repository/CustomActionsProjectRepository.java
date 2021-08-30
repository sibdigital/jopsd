package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.CustomActionsProject;

@Repository
public interface CustomActionsProjectRepository extends JpaRepository<CustomActionsProject, Long>, JpaSpecificationExecutor<CustomActionsProject> {
}