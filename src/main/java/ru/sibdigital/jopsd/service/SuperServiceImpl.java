package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.WorkPackageTarget;
import ru.sibdigital.jopsd.repository.*;
import ru.sibdigital.jopsd.repository.RelationRepo;
import ru.sibdigital.jopsd.repository.WorkPackageRepo;
import ru.sibdigital.jopsd.service.elbudget.execution.RiskService;
import ru.sibdigital.jopsd.service.elbudget.execution.TargetService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
    protected RiskService riskService;

    @Autowired
    protected ProjectRepo projectRepo;

    @Autowired
    protected WorkPackageTargetRepo workPackageTargetRepo;

    @Autowired
    protected TargetService targetService;

    @Autowired
    protected CostObjectRepo costObjectRepo;

    protected void logError(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
    }

    protected void logError(String errorMessage) {
        log.error(errorMessage);
        System.out.println(errorMessage);
    }

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
}
