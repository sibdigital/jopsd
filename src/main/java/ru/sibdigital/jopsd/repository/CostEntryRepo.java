package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.CostEntry;

import java.util.List;

@Repository
public interface CostEntryRepo extends JpaRepository<CostEntry, Long>, JpaSpecificationExecutor<CostEntry> {
    @Query(nativeQuery = true,
    value = "select * \n" +
            "from cost_entries\n" +
            "where work_package_id in (:work_package_ids) and cost_type_id =:cost_type_id ")
    List<CostEntry> findAllByWorkPackageIdsAndCostTypeId(@Param("work_package_ids") List<Long> workPackageIds,
                                                         @Param("cost_type_id") Long costTypeId);
}
