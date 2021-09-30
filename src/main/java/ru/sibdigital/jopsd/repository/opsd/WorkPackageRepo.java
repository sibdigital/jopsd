package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface WorkPackageRepo extends JpaRepository<WorkPackage, Long>, JpaSpecificationExecutor<WorkPackage> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM work_packages WHERE outer_id in :outer_ids")
    List<WorkPackage> findAllByOuterIds(@Param("outer_ids") List<Long> outerId);

    List<WorkPackage> findAllByCostObjectId(Long costObjectId);

//    List<WorkPackage> findAllByProjectId(Long projectId);
    Page<WorkPackage> findAllByProject_Id(Long projectId, Pageable pageable);
    Page<WorkPackage> findByProject_IdAndSubjectContainingIgnoreCase(Long projectId, String subject, Pageable pageable);

    Optional<WorkPackage> findWorkPackageByMetaId(Long metaId);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(WorkPackage workPackage);
}
