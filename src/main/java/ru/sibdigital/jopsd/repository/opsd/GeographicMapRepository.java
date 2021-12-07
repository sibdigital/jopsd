package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.GeographicMap;

import java.util.List;

@RepositoryRestResource
public interface GeographicMapRepository extends JpaRepository<GeographicMap, Long>, JpaSpecificationExecutor<GeographicMap> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(GeographicMap geographicMap);

    List<GeographicMap> findAllByProject_Id(Long projectId);
}
