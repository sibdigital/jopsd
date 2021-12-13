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

    List<WorkPackage> findAllByProjectId(Long projectId);

    Page<WorkPackage> findWorkPackagesByProject_Id(Long projectId, Pageable pageable);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(WorkPackage workPackage);

    @Query(nativeQuery = true, value ="SELECT wp.*\n" +
            "FROM work_packages wp\n" +
            "INNER JOIN work_package_targets wpt\n" +
            "    ON wp.id = wpt.work_package_id\n" +
            "INNER JOIN targets\n" +
            "    ON wpt.target_id = targets.id\n" +
            "INNER JOIN projects\n" +
            "    ON wp.project_id = projects.id\n" +
            "WHERE\n" +
            "    wp.id = :id_wp\n" +
            "    AND wp.meta_id IS NOT NULL\n" +
            "    AND targets.meta_id IS NOT NULL AND targets.type_id = 41\n" +
            "    AND wpt.month = 12 AND wpt.year = :year\n" +
            "    AND wp.project_id IS NOT NULL")
    WorkPackage getWorkPackageReadyForEB(@Param("id_wp") Long idWorkPackage, @Param("year") Integer year);

    @Query(value = "SELECT *\n" +
            "FROM work_packages\n" +
            "WHERE current_timestamp > due_date\n" +
            "  AND assigned_to_id = :idUser\n" +
            "  AND project_id = :idProject\n" +
            "  AND status_id in (1, 2, 6)"
            , nativeQuery = true)
    List<WorkPackage> findExpiredWorkPackagesByUserIdAndProject(Long idUser, Long idProject);

    @Query(value = "SELECT * FROM work_packages\n" +
            "WHERE project_id =:id\n" +
            "AND current_timestamp > due_date\n" +
            "AND status_id in (1,2,6)"
            , nativeQuery = true)
    List<WorkPackage> findExpiredWorkPackagesByProjectId(Long id);

    @Query(value = "SELECT * FROM work_packages\n" +
            "WHERE project_id =:id\n" +
            "AND cast(due_date as date) - CURRENT_DATE < 14\n" +
            "AND cast(due_date as date) - CURRENT_DATE >= 0\n" +
            "AND status_id in (1,2,6)"
            , nativeQuery = true)
    List <WorkPackage> findWorkPackagesOverDays(Long id);

    @Query(value = "SELECT * " +
            "FROM work_packages " +
            "WHERE status_id IN (1,2,6) " +
            "AND project_id =:id"
            , nativeQuery = true)
    List <WorkPackage> findAllByProjectIdAndStatuses(Long id);

    @Query(value = "SELECT *\n" +
            "FROM work_packages\n" +
            "WHERE assigned_to_id =:idUser\n" +
            "AND project_id =:idProject\n" +
            "AND status_id in (1,2,6)"
            , nativeQuery = true)
    List <WorkPackage> findAllByUserIdIdAndStatuses(Long idUser, Long idProject);

    @Query(value = "SELECT *\n" +
            "FROM work_packages\n" +
            "WHERE current_timestamp > due_date\n" +
            "AND assigned_to_id =:idUser\n" +
            "AND status_id in (1,2,6)"
            , nativeQuery = true)
    List<WorkPackage> findExpiredWorkPackagesByUserId(Long idUser);
}
