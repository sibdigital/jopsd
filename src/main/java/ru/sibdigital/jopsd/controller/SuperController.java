package ru.sibdigital.jopsd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.sibdigital.jopsd.config.ApplicationConstants;
import ru.sibdigital.jopsd.service.ProjectService;
import ru.sibdigital.jopsd.service.mp.MPService;

@Slf4j
@Controller
public class SuperController {
    @Autowired
    protected ApplicationConstants applicationConstants;

    @Autowired
    protected MPService MPService;

    @Autowired
    protected ProjectService projectService;

    protected void logError(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    }

    protected void logError(String errorMessage) {
        log.error(errorMessage);
        System.out.println(errorMessage);
    }
}
