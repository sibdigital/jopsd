package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {

    @Query(value = "SELECT *\n" +
                    "FROM projects\n" +
                    "WHERE lft IS NOT NULL\n" +
                    "ORDER BY lft DESC\n" +
                    "LIMIT 1;", nativeQuery = true)
    Project findProjectWithMaxLft();
}
