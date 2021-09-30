package ru.sibdigital.jopsd.service.page;

import ru.sibdigital.jopsd.model.opsd.PageFile;

public interface PageFileService {
    PageFile createOrUpdatePageFile(PageFile pageFile);
    PageFile deletePageFile(Long id);
}
