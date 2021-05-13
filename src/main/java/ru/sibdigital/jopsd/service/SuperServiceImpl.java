package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.repository.ProjectRepo;
import ru.sibdigital.jopsd.repository.RelationRepo;
import ru.sibdigital.jopsd.repository.WorkPackageRepo;

@Service
@Slf4j
public class SuperServiceImpl implements SuperService {

    @Autowired
    protected WorkPackageRepo workPackageRepo;

    @Autowired
    protected RelationRepo relationRepo;

    @Autowired
    protected ProjectRepo projectRepo;

    protected void logError(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    }

    protected void logError(String errorMessage) {
        log.error(errorMessage);
        System.out.println(errorMessage);
    }
}
