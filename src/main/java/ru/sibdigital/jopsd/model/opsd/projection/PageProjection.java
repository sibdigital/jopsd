package ru.sibdigital.jopsd.model.opsd.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.sibdigital.jopsd.model.opsd.Page;
import ru.sibdigital.jopsd.model.opsd.Project;
import ru.sibdigital.jopsd.model.opsd.User;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

import java.time.LocalDateTime;

@Projection(name = "pageProjection", types = {Page.class})
public interface PageProjection {
    Long getId();
    String getTitle();
    String getContent();
    Boolean getIsGroup();
    Boolean getIsPublicated();
    Boolean getIsDeleted();
    User getAuthor();
    Project getProject();
    WorkPackage getWorkPackage();
    Page getParent();
    LocalDateTime getUpdatedOn();
    LocalDateTime getCreatedOn();
}
