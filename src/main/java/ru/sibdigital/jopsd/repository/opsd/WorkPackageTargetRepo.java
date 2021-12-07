package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;
import ru.sibdigital.jopsd.model.opsd.WorkPackageTarget;

import java.util.List;

@RepositoryRestResource
public interface WorkPackageTargetRepo extends JpaRepository<WorkPackageTarget, Long>, JpaSpecificationExecutor<WorkPackageTarget> {
    List<WorkPackageTarget> findAllByWorkPackageIdAndTargetIdAndYear(Long workPackageId, Long targetId, Integer year);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(WorkPackageTarget workPackageTarget);

    @Query(nativeQuery = true, value = "SELECT wpt.*\n" +
            "FROM work_package_targets wpt\n" +
            "INNER JOIN targets t\n" +
            "    ON wpt.target_id = t.id\n" +
            "WHERE wpt.work_package_id = :id_wp\n" +
            "    AND wpt.year = :year\n" +
            "    AND t.type_id = 41 AND t.meta_id IS NOT NULL\n" +
            "LIMIT 1;")
    WorkPackageTarget findWorkPackageTargetAsRpResultIndicator(@Param("id_wp") Long idWorkPackage, @Param("year") Integer year);

    List<WorkPackageTarget> findAllByWorkPackage(WorkPackage workPackage);
}
