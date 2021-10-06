package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.ContractDto;
import ru.sibdigital.jopsd.model.opsd.*;

import java.sql.Timestamp;


@Service
@Slf4j
public class ContractServiceImpl extends SuperServiceImpl implements ContractService{
    @Override
    public Contract saveContract(ContractDto contractDto) {
        Project project = (contractDto.getProjectId() != null) ? projectRepo.findById(contractDto.getProjectId()).orElse(null) : null;
        Contract contract = Contract.builder()
                            .id(contractDto.getId())
                            .contractSubject(contractDto.getContractSubject())
                            .contractDate(convertToLocalDateTimeViaInstant(contractDto.getContractDate()))
                            .price(contractDto.getPrice())
                            .executor(contractDto.getExecutor())
//                            .createdAt(contractDto.getCreatedAt())
//                            .createdAt(new Timestamp(System.currentTimeMillis()))
                            .updatedAt(new Timestamp(System.currentTimeMillis()))
                            .contractNum(contractDto.getContractNum())
                            .isApprove(contractDto.getIsApprove())
                            .eisHref(contractDto.getEisHref())
                            .name(contractDto.getName())
                            .sposob(contractDto.getSposob())
                            .gosZakaz(contractDto.getGosZakaz())
                            .dateBegin(convertToLocalDateTimeViaInstant(contractDto.getDateBegin()))
                            .dateEnd(convertToLocalDateTimeViaInstant(contractDto.getDateEnd()))
                            .etaps(contractDto.getEtaps())
                            .project(project)
                            .auctionDate(convertToLocalDateTimeViaInstant(contractDto.getAuctionDate()))
                            .scheduleDate(convertToLocalDateTimeViaInstant(contractDto.getScheduleDate()))
                            .nmck(contractDto.getNmck())
                            .scheduleDatePlan(convertToLocalDateTimeViaInstant(contractDto.getScheduleDatePlan()))
                            .notificationDate(convertToLocalDateTimeViaInstant(contractDto.getNotificationDate()))
                            .notificationDatePlan(convertToLocalDateTimeViaInstant(contractDto.getNotificationDatePlan()))
                            .auctionDatePlan(convertToLocalDateTimeViaInstant(contractDto.getAuctionDatePlan()))
                            .contractDatePlan(convertToLocalDateTimeViaInstant(contractDto.getContractDatePlan()))
                            .dateEndPlan(convertToLocalDateTimeViaInstant(contractDto.getDateEndPlan()))
                            .note(contractDto.getNote())
                            .conclusionOfEstimatedCostDetails(contractDto.getConclusionOfEstimatedCostDetails())
                            .conclusionOfEstimatedCostNumber(contractDto.getConclusionOfEstimatedCostNumber())
                            .conclusionOfEstimatedCostDate(convertToLocalDateTimeViaInstant(contractDto.getConclusionOfEstimatedCostDate()))
                            .build();

        contractRepository.save(contract);
        return null;
    }
}
