package ru.sibdigital.jopsd.service.page;

import ru.sibdigital.jopsd.model.opsd.Page;

import java.util.List;

public interface PageService {
    Page createOrUpdatePage(Page page);
    Page deletePage(Long id);
    List<Page> getGroups();
}
