package ru.sibdigital.jopsd.service.page;

import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.PageMap;
import ru.sibdigital.jopsd.service.SuperServiceImpl;


@Service
public class PageMapServiceImpl extends SuperServiceImpl implements PageMapService {
    @Override
    public PageMap createOrUpdatePageMap(PageMap pageMap) {
        pageMapRepository.save(pageMap);
        return pageMap;
    }
}
