package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.CustomActionsProject;

@Repository
public interface CustomActionsProjectRepository extends JpaRepository<CustomActionsProject, Long>, JpaSpecificationExecutor<CustomActionsProject> {
}