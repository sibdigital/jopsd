package ru.sibdigital.jopsd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.sibdigital.jopsd.config.ApplicationConstants;
import ru.sibdigital.jopsd.repository.opsd.CostObjectRepo;
import ru.sibdigital.jopsd.service.*;
import ru.sibdigital.jopsd.service.elbudget.execution.*;
import ru.sibdigital.jopsd.service.mp.MPService;
import ru.sibdigital.jopsd.service.opsd.CostTypeService;
import ru.sibdigital.jopsd.service.opsd.LboService;
import ru.sibdigital.jopsd.service.opsd.RateService;
import ru.sibdigital.jopsd.service.page.PageFileService;
import ru.sibdigital.jopsd.service.page.PageMapService;
import ru.sibdigital.jopsd.service.page.PageService;
import ru.sibdigital.jopsd.service.report.KpiReportService;
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
    protected KpiService kpiService;

    @Autowired
    protected PositionService positionService;

    @Autowired
    protected MapPointService mapPointService;

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

    @Autowired
    protected RateService rateService;

    @Autowired
    protected CostTypeService costTypeService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected KpiReportService kpiReportService;

    protected void logError(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    }
    @Autowired
    protected ExportExecutionService exportExecutionService;

    @Autowired
    protected RiskService riskService;

    @Autowired
    protected ExecutionParseService executionParseService;
}
