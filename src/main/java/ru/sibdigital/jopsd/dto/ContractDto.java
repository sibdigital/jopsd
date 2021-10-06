package ru.sibdigital.jopsd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sibdigital.jopsd.model.opsd.Project;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    private Long id;
    private Long projectId;
    private String contractSubject;
    private Date contractDate;
    private BigDecimal price;
    private String executor;
    private String createdAt;
    private String updatedAt;
    private String contractNum;
    private Boolean isApprove;
    private String eisHref;
    private String name;
    private String sposob;
    private String gosZakaz;
    private Date dateBegin;
    private Date dateEnd;
    private String etaps;
    private Date auctionDate;
    private Date scheduleDate;
    private BigDecimal nmck;
    private Date scheduleDatePlan;
    private Date notificationDatePlan;
    private Date notificationDate;
    private Date auctionDatePlan;
    private Date contractDatePlan;
    private Date dateEndPlan;
    private String note;
    private String conclusionOfEstimatedCostDetails;
    private String conclusionOfEstimatedCostNumber;
    private Date conclusionOfEstimatedCostDate;
    private String conclusionOfProjectDocumentationDetails;
    private String conclusionOfProjectDocumentationNumber;
    private Date conclusionOfProjectDocumentationDate;
    private String conclusionOfEcologicalExpertiseDetails;
    private String conclusionOfEcologicalExpertiseNumber;
    private Date conclusionOfEcologicalExpertiseDate;
}
