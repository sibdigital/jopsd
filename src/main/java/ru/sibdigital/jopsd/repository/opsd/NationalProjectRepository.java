package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.NationalProject;

@Repository
public interface NationalProjectRepository extends JpaRepository<NationalProject, Long>, JpaSpecificationExecutor<NationalProject> {

    Page findByType(@Param("type") String type, Pageable p);
}