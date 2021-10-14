package ru.sibdigital.jopsd.model.opsd.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.sibdigital.jopsd.model.opsd.Page;

import java.util.List;

@Projection(name = "pageShortProjection", types = {Page.class})
public interface PageShortProjection {
    Long getId();
    String getTitle();
    Boolean getIsPublicated();
    Boolean getIsDeleted();
    List<PageShortProjection> getChildren();
}
