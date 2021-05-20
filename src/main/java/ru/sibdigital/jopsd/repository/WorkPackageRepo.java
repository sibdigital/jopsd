package ru.sibdigital.jopsd.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.WorkPackage;

import java.util.List;

@Repository
public interface WorkPackageRepo extends JpaRepository<WorkPackage, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM work_packages WHERE outer_id in :outer_ids")
    List<WorkPackage> findAllByOuterIds(@Param("outer_ids") List<Long> outerId);

    List<WorkPackage> findAllByCostObjectId(Long costObjectId);
}
