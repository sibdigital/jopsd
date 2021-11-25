package ru.sibdigital.jopsd.model.opsd.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.sibdigital.jopsd.model.opsd.Organization;
import ru.sibdigital.jopsd.model.opsd.Project;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

@Projection(name = "shortProjection", types = {WorkPackage.class})
public interface WorkPackageShortProjection {
    Long getId();
    Project getProject();
    String getSubject();
    String getDescription();
    Organization getOrganization();
    Long getMetaId();
}
