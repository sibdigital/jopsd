package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Relation;

import java.util.List;

@RepositoryRestResource
public interface RelationRepo extends JpaRepository<Relation, Long>, JpaSpecificationExecutor<Relation> {

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

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(Relation relation);
}
