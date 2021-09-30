package ru.sibdigital.jopsd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.sibdigital.jopsd.repository.opsd.CostObjectRepo;
import ru.sibdigital.jopsd.repository.opsd.ProjectRepo;
import ru.sibdigital.jopsd.repository.opsd.TargetRepo;
import ru.sibdigital.jopsd.repository.opsd.WorkPackageRepo;
import ru.sibdigital.jopsd.service.elbudget.execution.ExecutionService;
import ru.sibdigital.jopsd.service.elbudget.execution.FinancialService;
import ru.sibdigital.jopsd.service.elbudget.execution.TargetService;
import ru.sibdigital.jopsd.service.mp.MPService;
import ru.sibdigital.jopsd.config.ApplicationConstants;
import ru.sibdigital.jopsd.service.ProjectService;
import ru.sibdigital.jopsd.service.page.PageFileService;
import ru.sibdigital.jopsd.service.page.PageMapService;
import ru.sibdigital.jopsd.service.page.PageService;

@Slf4j
@Controller
public class SuperController {
    @Autowired
    protected ApplicationConstants applicationConstants;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected MPService MPService;

    @Autowired
    protected ExecutionService executionService;

    @Autowired
    protected CostObjectRepo costObjectRepo;

    @Autowired
    protected WorkPackageRepo workPackageRepo; //TODO убрать, когда в service обернуто все будет

    @Autowired
    protected ProjectRepo projectRepo; //TODO убрать, когда в service обернуто все будет

    @Autowired
    protected TargetRepo targetRepo; //TODO убрать, когда в service обернуто все будет

    @Autowired
    protected FinancialService financialService;

    @Autowired
    protected TargetService targetService;

    @Autowired
    protected PageService pageService;

    @Autowired
    protected PageFileService pageFileService;

    @Autowired
    protected PageMapService pageMapService;

    protected void logError(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    }

    protected void logError(String errorMessage) {
        log.error(errorMessage);
        System.out.println(errorMessage);
    }
}
