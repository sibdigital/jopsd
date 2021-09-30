package ru.sibdigital.jopsd.service.page;

import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.PageFile;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

@Service
public class PageFileServiceImpl extends SuperServiceImpl implements PageFileService {
    @Override
    public PageFile createOrUpdatePageFile(PageFile pageFile) {
        pageFile.setPage(pageRepository.findById(pageFile.getPage().getId()).orElse(null));
        pageFileRepository.save(pageFile);
        return pageFile;
    }

    @Override
    public PageFile deletePageFile(Long id) {
        PageFile pageFile = pageFileRepository.findById(id).orElseThrow(NullPointerException::new);
        if (pageFile != null) {
            pageFile.setIsDeleted(true);
            pageFileRepository.save(pageFile);
        }
        return pageFile;
    }
}
