package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.ContractDto;
import ru.sibdigital.jopsd.model.opsd.*;
import ru.sibdigital.jopsd.utils.DateTimeUtils;

import java.sql.Timestamp;


@Service
@Slf4j
public class ContractServiceImpl extends SuperServiceImpl implements ContractService{
    @Override
    public Contract saveContract(ContractDto contractDto) {
        Project project = (contractDto.getProjectId() != null) ? projectRepo.findById(contractDto.getProjectId()).orElse(null) : null;
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Boolean isApprove = true;
        if (contractDto.getId() != null) {
            Contract prevVersion = contractRepository.findById(contractDto.getId()).orElse(null);
            if (prevVersion != null) {
                createdAt = prevVersion.getCreatedAt();
                isApprove = prevVersion.getIsApprove();
                project   = prevVersion.getProject();
            }
        }
        Contract contract = Contract.builder()
                            .id(contractDto.getId())
                            .contractSubject(contractDto.getContractSubject())
                            .contractDate(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getContractDate()))
                            .price(contractDto.getPrice())
                            .executor(contractDto.getExecutor())
                            .createdAt(createdAt)
                            .updatedAt(new Timestamp(System.currentTimeMillis()))
                            .contractNum(contractDto.getContractNum())
                            .isApprove(isApprove)
                            .eisHref(contractDto.getEisHref())
                            .name(contractDto.getName())
                            .sposob(contractDto.getSposob())
                            .gosZakaz(contractDto.getGosZakaz())
                            .dateBegin(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getDateBegin()))
                            .dateEnd(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getDateEnd()))
                            .etaps(contractDto.getEtaps())
                            .project(project)
                            .auctionDate(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getAuctionDate()))
                            .scheduleDate(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getScheduleDate()))
                            .nmck(contractDto.getNmck())
                            .scheduleDatePlan(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getScheduleDatePlan()))
                            .notificationDate(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getNotificationDate()))
                            .notificationDatePlan(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getNotificationDatePlan()))
                            .auctionDatePlan(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getAuctionDatePlan()))
                            .contractDatePlan(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getContractDatePlan()))
                            .dateEndPlan(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getDateEndPlan()))
                            .note(contractDto.getNote())
                            .conclusionOfEstimatedCostDetails(contractDto.getConclusionOfEstimatedCostDetails())
                            .conclusionOfEstimatedCostNumber(contractDto.getConclusionOfEstimatedCostNumber())
                            .conclusionOfEstimatedCostDate(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getConclusionOfEstimatedCostDate()))
                            .conclusionOfProjectDocumentationDetails(contractDto.getConclusionOfProjectDocumentationDetails())
                            .conclusionOfProjectDocumentationNumber(contractDto.getConclusionOfProjectDocumentationNumber())
                            .conclusionOfProjectDocumentationDate(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getConclusionOfProjectDocumentationDate()))
                            .conclusionOfEcologicalExpertiseDate(DateTimeUtils.convertToLocalDateTimeViaInstant(contractDto.getConclusionOfEcologicalExpertiseDate()))
                            .conclusionOfEcologicalExpertiseNumber(contractDto.getConclusionOfEcologicalExpertiseNumber())
                            .conclusionOfEcologicalExpertiseDetails(contractDto.getConclusionOfEcologicalExpertiseDetails())
                            .build();

        contractRepository.save(contract);
        return contract;
    }
}
