package ru.sibdigital.jopsd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.sibdigital.jopsd.repository.opsd.CostObjectRepo;
import ru.sibdigital.jopsd.service.ContractService;
import ru.sibdigital.jopsd.service.SettingService;
import ru.sibdigital.jopsd.service.WorkPackageService;
import ru.sibdigital.jopsd.service.elbudget.execution.ExecutionService;
import ru.sibdigital.jopsd.service.elbudget.execution.FinancialService;
import ru.sibdigital.jopsd.service.elbudget.execution.TargetService;
import ru.sibdigital.jopsd.service.mp.MPService;
import ru.sibdigital.jopsd.config.ApplicationConstants;
import ru.sibdigital.jopsd.service.ProjectService;
import ru.sibdigital.jopsd.service.opsd.LboService;
import ru.sibdigital.jopsd.service.page.PageFileService;
import ru.sibdigital.jopsd.service.page.PageMapService;
import ru.sibdigital.jopsd.service.page.PageService;
import ru.sibdigital.jopsd.service.report.LightService;

@Slf4j
@Controller
public class SuperController {
    @Autowired
    protected ApplicationConstants applicationConstants;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected WorkPackageService workPackageService;

    @Autowired
    protected MPService MPService;

    @Autowired
    protected ExecutionService executionService;

    @Autowired
    protected CostObjectRepo costObjectRepo;

    @Autowired
    protected FinancialService financialService;

    @Autowired
    protected TargetService targetService;

    @Autowired
    protected SettingService settingService;

    @Autowired
    protected PageService pageService;

    @Autowired
    protected PageFileService pageFileService;

    @Autowired
    protected PageMapService pageMapService;

    @Autowired
    protected ContractService contractService;

    @Autowired
    protected LightService lightService;

    @Autowired
    protected LboService lboService;

    protected void logError(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    }

    protected void logError(String errorMessage) {
        log.error(errorMessage);
        System.out.println(errorMessage);
    }
}
