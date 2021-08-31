package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkPackageRepo extends JpaRepository<WorkPackage, Long>, JpaSpecificationExecutor<WorkPackage> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM work_packages WHERE outer_id in :outer_ids")
    List<WorkPackage> findAllByOuterIds(@Param("outer_ids") List<Long> outerId);

    List<WorkPackage> findAllByCostObjectId(Long costObjectId);

    List<WorkPackage> findAllByProjectId(Long projectId);

    Optional<WorkPackage> findWorkPackageByMetaId(Long metaId);
}
