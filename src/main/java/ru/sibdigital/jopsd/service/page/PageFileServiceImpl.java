package ru.sibdigital.jopsd.service.page;

import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.PageFile;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

@Service
public class PageFileServiceImpl extends SuperServiceImpl implements PageFileService {
    @Override
    public PageFile createOrUpdatePageFile(PageFile pageFile) {
        pageFileRepository.save(pageFile);
        return pageFile;
    }
}
