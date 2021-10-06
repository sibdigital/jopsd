package ru.sibdigital.jopsd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.sibdigital.jopsd.repository.opsd.CostObjectRepo;
import ru.sibdigital.jopsd.service.ContractService;
import ru.sibdigital.jopsd.service.SettingService;
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
    protected FinancialService financialService;

    @Autowired
    protected TargetService targetService;

    @Autowired
    protected SettingService settingService;

    @Autowired
    protected ContractService contractService;

    protected void logError(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    }

    protected void logError(String errorMessage) {
        log.error(errorMessage);
        System.out.println(errorMessage);
    }
}
