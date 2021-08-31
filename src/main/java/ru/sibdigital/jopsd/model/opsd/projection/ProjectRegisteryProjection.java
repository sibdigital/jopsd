package ru.sibdigital.jopsd.model.opsd.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import ru.sibdigital.jopsd.model.opsd.Enumeration;
import ru.sibdigital.jopsd.model.opsd.NationalProject;
import ru.sibdigital.jopsd.model.opsd.Project;

import java.sql.Timestamp;

@Projection(name = "projectRegisteryProjection", types = { Project.class })
public interface ProjectRegisteryProjection {
    String getName();
    Boolean getPublic();
    NationalProject getFederalProject();
    Enumeration getProjectStatus();
    Enumeration setProjectApproveStatus();

//    @Value("#{@projectRepo.getFilesize(target.id)}")
//    Double getFilesize();

    Timestamp getStartDate();
    Timestamp getDueDate();
}
