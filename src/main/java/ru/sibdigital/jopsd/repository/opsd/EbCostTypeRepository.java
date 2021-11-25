package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.EbCostType;

import java.util.List;

@RepositoryRestResource
public interface EbCostTypeRepository extends JpaRepository<EbCostType, Long>, JpaSpecificationExecutor<EbCostType> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(EbCostType ebCostType);


    @Query(value = "SELECT ect.*\n" +
            "FROM reg_eb_cost_types rect\n" +
            "    LEFT JOIN eb_cost_types ect\n" +
            "        ON rect.id_eb_cost_type = ect.id\n" +
            "WHERE rect.id_cost_type = :id_cost_type", nativeQuery = true)
    List<EbCostType> getEbCostTypesByCostTypeId(@Param("id_cost_type") Long costTypeId);


    @Query(value = "SELECT ect.*\n" +
            "    FROM eb_cost_types ect\n" +
            "    LEFT JOIN reg_eb_cost_types rect\n" +
            "    ON ect.id = rect.id_eb_cost_type\n" +
            "    WHERE rect.id is null\n" +
            "    ORDER BY ect.id", nativeQuery = true)
    List<EbCostType> getFreeEbCostTypes();
}