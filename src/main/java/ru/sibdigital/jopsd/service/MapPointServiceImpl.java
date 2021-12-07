package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.MapPoint;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;

@Service
@Slf4j
public class MapPointServiceImpl extends SuperServiceImpl implements MapPointService{

    @Override
    public MapPoint save(MapPoint mapPoint) {
        final MapPoint save = mapPointRepository.save(mapPoint);
        final WorkPackage wp = save.getWorkPackage();
        save.setWorkPackage(wp != null ? workPackageRepo.findById(wp.getId()).orElse(null) : null);
        return save;
    }
}
