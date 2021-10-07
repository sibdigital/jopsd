package ru.sibdigital.jopsd.service.page;

import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.Page;
import ru.sibdigital.jopsd.model.opsd.User;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PageServiceImpl extends SuperServiceImpl implements PageService {
    @Override
    public Page createOrUpdatePage(Page page, User currentUser) {
        page.setAuthor(currentUser);
        if (page.getCreatedOn() == null) {
            page.setCreatedOn(LocalDateTime.now());
        }
        page.setUpdatedOn(LocalDateTime.now());
        pageRepository.save(page);
        return page;
    }

    @Override
    public List<Page> getGroups(Long id){
        if (id == null) {
            return pageRepository.findAllByIsGroup(true);
        } else {
            return pageRepository.findAllByIsGroupAndIdIsNot(true, id);
        }
    }
}
