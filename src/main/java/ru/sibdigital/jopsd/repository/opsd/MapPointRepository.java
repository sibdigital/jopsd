package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.MapPoint;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource()
public interface MapPointRepository extends JpaRepository<MapPoint, Long>, JpaSpecificationExecutor<MapPoint> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(MapPoint mapPoint);

    @Override
    @EntityGraph(value = "map-point-entity-graph")
    MapPoint save(MapPoint entity);

    @Override
    @EntityGraph(value = "map-point-entity-graph")
    Optional<MapPoint> findById(Long id);

    List<MapPoint> findAllByIsDeletedFalseAndGeographicMap_Id(Long geographicMapId);
}
