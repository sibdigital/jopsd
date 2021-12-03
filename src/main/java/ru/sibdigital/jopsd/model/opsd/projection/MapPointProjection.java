package ru.sibdigital.jopsd.model.opsd.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.sibdigital.jopsd.model.opsd.MapPoint;
import ru.sibdigital.jopsd.model.opsd.Project;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

@Projection(name = "mapPointProjection", types = {MapPoint.class})
public interface MapPointProjection {
    Long getId();
    String getTitle();
    Double getLongitude();
    Double getLatitude();
    String getDescription();
    Boolean getDeleted();
    WorkPackage getWorkPackage();
    Project getProject();
}
