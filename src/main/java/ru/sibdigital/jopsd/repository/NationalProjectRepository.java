package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.NationalProject;

@Repository
public interface NationalProjectRepository extends JpaRepository<NationalProject, Long>, JpaSpecificationExecutor<NationalProject> {
}