package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.CostEntry;

import java.util.List;

@RepositoryRestResource
public interface CostEntryRepo extends JpaRepository<CostEntry, Long>, JpaSpecificationExecutor<CostEntry> {
    @Query(nativeQuery = true,
    value = "select * \n" +
            "from cost_entries\n" +
            "where work_package_id in (:work_package_ids) and cost_type_id =:cost_type_id ")
    List<CostEntry> findAllByWorkPackageIdsAndCostTypeId(@Param("work_package_ids") List<Long> workPackageIds,
                                                         @Param("cost_type_id") Long costTypeId);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(CostEntry costEntry);
}
