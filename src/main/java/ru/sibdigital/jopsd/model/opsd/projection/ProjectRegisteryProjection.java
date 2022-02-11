package ru.sibdigital.jopsd.model.opsd.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import ru.sibdigital.jopsd.model.opsd.Enumeration;
import ru.sibdigital.jopsd.model.opsd.NationalProject;
import ru.sibdigital.jopsd.model.opsd.Project;

import java.sql.Timestamp;

@Projection(name = "projectRegisteryProjection", types = { Project.class })
public interface ProjectRegisteryProjection {
    Long getId();
    String getName();
    Boolean getPublic();

    NationalProject getFederalProject();
    NationalProject getNationalProject();

    Enumeration getProjectStatus();
    Enumeration getProjectApproveStatus();
    Integer getStatus();

    String getIdentifier();

    String getDescription();

    @Value("#{@projectRepo.findDoneRatio(target.id)}")
    Double getDoneRatio();

    Timestamp getStartDate();
    Timestamp getDueDate();
    Timestamp getUpdatedOn();
    Timestamp getCreatedOn();
}
