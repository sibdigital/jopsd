package ru.sibdigital.jopsd.service.page;

import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.PageMap;
import ru.sibdigital.jopsd.service.SuperServiceImpl;


@Service
public class PageMapServiceImpl extends SuperServiceImpl implements PageMapService {
    @Override
    public PageMap createOrUpdatePageMap(PageMap pageMap) {
        pageMap.setPage(pageRepository.findById(pageMap.getPage().getId()).orElse(null));
        pageMapRepository.save(pageMap);
        return pageMap;
    }

    @Override
    public PageMap deletePageMap(Long id) {
        PageMap pageMap = pageMapRepository.findById(id).orElseThrow(NullPointerException::new);
        if (pageMap != null) {
            pageMap.setIsDeleted(true);
            pageMapRepository.save(pageMap);
        }
        return pageMap;
    }
}
