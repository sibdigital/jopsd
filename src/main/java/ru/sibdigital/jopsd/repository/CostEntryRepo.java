package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.CostEntry;

import java.util.List;

@Repository
public interface CostEntryRepo extends JpaRepository<CostEntry, Long> {
    @Query(nativeQuery = true,
    value = "select * from cost_enselect *\n" +
            "from cost_entries\n" +
            "where cost_type_id =:cost_type_id and work_package_id in (:work_package_ids)")
    List<CostEntry> findAllByWorkPackageIdsAnAndCostTypeId(@Param("cost_type_id") Long costTypeId,
                                                           @Param("work_package_ids") List<Long> workPackageIds);
}
