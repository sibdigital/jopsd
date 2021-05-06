package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.Relation;

import java.util.List;

@Repository
public interface RelationRepo extends JpaRepository<Relation, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "DELETE FROM relations\n" +
            "WHERE to_id in :work_packages_ids\n" +
            "  AND relations.relates = 0\n" +
            "  AND relations.duplicates = 0\n" +
            "  AND relations.blocks = 0\n" +
            "  AND relations.follows = 0\n" +
            "  AND relations.includes = 0\n" +
            "  AND relations.requires = 0;")
    void deleteWorkPackagesRelations(@Param("work_packages_ids") List<Long> workPackagesIds);
}
