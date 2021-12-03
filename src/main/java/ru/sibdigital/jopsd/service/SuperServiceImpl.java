package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.repository.opsd.*;
import ru.sibdigital.jopsd.service.elbudget.execution.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
public class SuperServiceImpl implements SuperService {

    @Autowired
    protected WorkPackageRepo workPackageRepo;

    @Autowired
    protected RelationRepo relationRepo;

    @Autowired
    protected RiskRepo riskRepo;

    @Autowired
    protected WorkPackageProblemRepo workPackageProblemRepo;

    @Autowired
    protected ProjectRepo projectRepo;

    @Autowired
    protected WorkPackageTargetRepo workPackageTargetRepo;

    @Autowired
    protected CostEntryRepo costEntryRepo;

    @Autowired
    protected CostObjectRepo costObjectRepo;

    @Autowired
    protected MaterialBudgetItemRepo materialBudgetItemRepo;

    @Autowired
    protected TargetRepo targetRepo;

    @Autowired
    protected BoardRepo boardRepo;

    @Autowired
    protected EnabledModuleRepo enabledModuleRepo;

    @Autowired
    protected EnumerationRepo enumerationRepo;

    @Autowired
    protected ProjectTypeRepo projectTypeRepo;

    @Autowired
    protected WikiRepo wikiRepo;

    @Autowired
    protected TargetExecutionValueRepo targetExecutionValueRepo;

    @Autowired
    protected RiskService riskService;

    @Autowired
    protected TargetService targetService;

    @Autowired
    protected ExecutionParseService executionParseService;

    @Autowired
    protected FinancialService financialService;

    @Autowired
    protected TranscriptorService transcriptorService;

    @Autowired
    protected ProjectService projectService;

    @Autowired
    protected WorkPackageService workPackageService;

    @Autowired
    protected TypeRepository typeRepository;

    @Autowired
    protected StatusRepository statusRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected CostTypeRepository costTypeRepository;

    @Autowired
    protected SettingRepository settingRepository;

    @Autowired
    protected PageRepository pageRepository;

    @Autowired
    protected PageFileRepository pageFileRepository;

    @Autowired
    protected PageMapRepository pageMapRepository;

    @Autowired
    protected KpiRepository kpiRepository;

    @Autowired
    protected KpiVariableRepository kpiVariableRepository;

    @Autowired
    protected MapPointRepository mapPointRepository;

    @Autowired
    protected ContractRepository contractRepository;

    @Autowired
    protected LboRepository lboRepository;

    @Autowired
    protected SettingService settingService;

    @Autowired
    protected MeetingRepository meetingRepository;

    @Autowired
    protected RateRepository rateRepository;

    @Autowired
    protected EbCostTypeRepository ebCostTypeRepository;

    @Autowired
    protected RegEbCostTypeRepository regEbCostTypeRepository;

    @Autowired
    protected OrganizationRepository organizationRepository;

    @Autowired
    protected RolePermissionRepository rolePermissionRepository;

    @Autowired
    protected TargetRiskRepository targetRiskRepository;

    protected static Unmarshaller getUnmarshaller(Class clazz) {
        Unmarshaller unmarshaller = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return unmarshaller;
    }

    protected static Marshaller getMarshaller(Class clazz) {
        Marshaller marshaller = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return marshaller;
    }

    protected Integer getQuarterByMonth(Integer monthValue) {
        if ((monthValue >= 1) && (monthValue <= 3)) {
            return 1;
        } else if ((monthValue >= 4) && (monthValue <= 6)) {
            return 2;
        } else if ((monthValue >= 7) && (monthValue <= 9)) {
            return 3;
        } else if ((monthValue >= 10) && (monthValue <= 12)) {
            return 4;
        }  else {
            return null;
        }
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        if (dateToConvert != null) {
            return dateToConvert.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        } else {
            return null;
        }
    }
}
