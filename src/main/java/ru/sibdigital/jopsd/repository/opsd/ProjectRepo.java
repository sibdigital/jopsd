package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.Project;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Query(value = "SELECT *\n" +
                    "FROM projects\n" +
                    "WHERE lft IS NOT NULL\n" +
                    "ORDER BY lft DESC\n" +
                    "LIMIT 1;", nativeQuery = true)
    Project findProjectWithMaxLft();

//    @Query(nativeQuery = true, value = "SELECT SUM(repo.required_storage_bytes) FROM repositories repo WHERE repo.project_id = :id")
//    Double getFilesize(@Param("id") Long projectId);

    Page<Project> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
