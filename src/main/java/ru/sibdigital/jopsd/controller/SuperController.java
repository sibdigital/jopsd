package ru.sibdigital.jopsd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.sibdigital.jopsd.model.WorkPackage;
import ru.sibdigital.jopsd.repository.CostObjectRepo;
import ru.sibdigital.jopsd.repository.ProjectRepo;
import ru.sibdigital.jopsd.repository.TargetRepo;
import ru.sibdigital.jopsd.repository.WorkPackageRepo;
import ru.sibdigital.jopsd.service.elbudget.execution.ExecutionService;
import ru.sibdigital.jopsd.service.elbudget.execution.FinancialService;
import ru.sibdigital.jopsd.service.elbudget.execution.TargetService;
import ru.sibdigital.jopsd.service.mp.MPService;
import ru.sibdigital.jopsd.config.ApplicationConstants;
import ru.sibdigital.jopsd.service.ProjectService;

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

    protected void logError(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    }

    protected void logError(String errorMessage) {
        log.error(errorMessage);
        System.out.println(errorMessage);
    }
}
