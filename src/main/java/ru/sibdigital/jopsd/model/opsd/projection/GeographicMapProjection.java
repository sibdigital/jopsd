package ru.sibdigital.jopsd.model.opsd.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.sibdigital.jopsd.model.opsd.GeographicMap;
import ru.sibdigital.jopsd.model.opsd.Project;

import java.util.List;

@Projection(name = "geographicMapProjection", types = {GeographicMap.class})
public interface GeographicMapProjection {
    Long getId();
    Boolean getDeleted();
    Project getProject();
    List<MapPointProjection> getMapPoints();
}
