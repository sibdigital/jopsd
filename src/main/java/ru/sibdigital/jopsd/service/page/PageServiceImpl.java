package ru.sibdigital.jopsd.service.page;

import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.Page;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.util.List;

@Service
public class PageServiceImpl extends SuperServiceImpl implements PageService {
    @Override
    public Page createOrUpdatePage(Page page) {
        page.setAuthor(userRepository.findById(page.getAuthor().getId()).orElse(null));
        page.setParent(pageRepository.findById(page.getParent().getId()).orElse(null));
        page.setWorkPackage(workPackageRepo.findById(page.getWorkPackage().getId()).orElse(null));
        page.setProject(projectRepo.findById(page.getProject().getId()).orElse(null));
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
