package ru.sibdigital.jopsd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.sibdigital.jopsd.service.elbudget.execution.ExecutionService;
import ru.sibdigital.jopsd.service.mp.MPService;

@Slf4j
@Controller
public class SuperController {
    @Autowired
    protected MPService MPService;

    @Autowired
    protected ExecutionService executionService;

    protected void logError(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    }

    protected void logError(String errorMessage) {
        log.error(errorMessage);
        System.out.println(errorMessage);
    }
}
