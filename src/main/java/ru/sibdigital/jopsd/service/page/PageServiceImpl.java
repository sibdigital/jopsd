package ru.sibdigital.jopsd.service.page;

import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.Page;
import ru.sibdigital.jopsd.model.opsd.User;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PageServiceImpl extends SuperServiceImpl implements PageService {
    @Override
    public Page createOrUpdatePage(Page page, User currentUser) {
        page.setAuthor(currentUser);
        if (page.getParent().getId() == null) page.setParent(null);
        if (page.getProject().getId() == null) page.setProject(null);
        if (page.getWorkPackage().getId() == null) page.setWorkPackage(null);
        if (page.getCreatedOn() == null) page.setCreatedOn(LocalDateTime.now());
        page.setUpdatedOn(LocalDateTime.now());
        pageRepository.save(page);
        return page;
    }

    @Override
    public Page deletePage(Long id) {
        Page page = pageRepository.findById(id).orElseThrow(NullPointerException::new);
        if (page != null) {
            page.setIsDeleted(true);
            pageRepository.save(page);
        }
        return page;
    }

    @Override
    public List<Page> getGroups(){
        return pageRepository.findAllByIsGroup(true);
    }
}
