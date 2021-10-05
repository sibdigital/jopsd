package ru.sibdigital.jopsd.service.page;

import ru.sibdigital.jopsd.model.opsd.Page;
import ru.sibdigital.jopsd.model.opsd.User;

import java.util.List;

public interface PageService {
    Page createOrUpdatePage(Page page, User currentUser);
    Page deletePage(Long id);
    List<Page> getGroups();
}
