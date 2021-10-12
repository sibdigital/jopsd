package ru.sibdigital.jopsd.model.report;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sibdigital.jopsd.dto.ContractDto;
import ru.sibdigital.jopsd.model.opsd.Contract;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;
import ru.sibdigital.jopsd.repository.opsd.ContractRepository;
import ru.sibdigital.jopsd.repository.opsd.WorkPackageRepo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity
public class SecondColorlight implements Serializable {
    @Id
    private Long workPackageId;
    @Id
    private Long contractId;
    @Id
    private Integer year;
    private String workPackageName;
    private BigDecimal paymentBalance; // Остаток по оплате контракта
    private String contractSubject;
    private BigDecimal nmck;
    private BigDecimal price;
    private Double spent;
    private Date scheduleDate;
    private Date scheduleDatePlan;
    private Date notificationDate;
    private Date notificationDatePlan;
    private Date auctionDate;
    private Date auctionDatePlan;
    private Date contractDate;
    private Date contractDatePlan;
    private Date dateEnd;
    private Date dateEndPlan;
    private String note;
    private Date conclusionOfEstimatedCostDate;
    private String conclusionOfEstimatedCostNumber;
    private String conclusionOfEstimatedCostDetails;
    private Date conclusionOfProjectDocumentationDate;
    private String conclusionOfProjectDocumentationNumber;
    private String conclusionOfProjectDocumentationDetails;
    private Date conclusionOfEcologicalExpertiseDate;
    private String conclusionOfEcologicalExpertiseNumber;
    private String conclusionOfEcologicalExpertiseDetails;

    @Transient
    private ContractDto contract;

    @Transient
    private BigDecimal economy;

    @PostLoad
    private void postLoad() {
        this.contract = ContractDto.builder()

                        .contractSubject(contractSubject)
                        .nmck(nmck)
                        .price(price)

                        .scheduleDate(scheduleDate) // план-график закупок
                        .scheduleDatePlan(scheduleDatePlan)

                        .notificationDate(notificationDate) // размещение извещения
                        .notificationDatePlan(notificationDatePlan)

                        .auctionDate(auctionDate) // проведение аукциона
                        .auctionDatePlan(auctionDatePlan)

                        .contractDate(contractDate)
                        .contractDatePlan(contractDatePlan)

                        .dateEnd(dateEnd)
                        .dateEndPlan(dateEndPlan)

                        .note(note)

                        .conclusionOfEstimatedCostDate(conclusionOfEstimatedCostDate)
                        .conclusionOfEstimatedCostNumber(conclusionOfEstimatedCostNumber)
                        .conclusionOfEstimatedCostDetails(conclusionOfEstimatedCostDetails)

                        .conclusionOfProjectDocumentationDate(conclusionOfProjectDocumentationDate)
                        .conclusionOfProjectDocumentationNumber(conclusionOfProjectDocumentationNumber)
                        .conclusionOfProjectDocumentationDetails(conclusionOfProjectDocumentationDetails)

                        .conclusionOfEcologicalExpertiseDate(conclusionOfEcologicalExpertiseDate)
                        .conclusionOfEcologicalExpertiseNumber(conclusionOfEcologicalExpertiseNumber)
                        .conclusionOfEcologicalExpertiseDetails(conclusionOfEcologicalExpertiseDetails)

                        .build();

        this.economy = Optional.ofNullable(this.nmck).orElse(BigDecimal.ZERO).subtract(
                Optional.ofNullable(this.price).orElse(BigDecimal.ZERO));
    }

    public SecondColorlight() {
    }

    public Long getWorkPackageId() {
        return workPackageId;
    }

    public void setWorkPackageId(Long workPackageId) {
        this.workPackageId = workPackageId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Double getSpent() {
        return spent;
    }

    public void setSpent(Double spent) {
        this.spent = spent;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getWorkPackageName() {
        return workPackageName;
    }

    public void setWorkPackageName(String workPackageName) {
        this.workPackageName = workPackageName;
    }

    public String getContractSubject() {
        return contractSubject;
    }

    public void setContractSubject(String contractSubject) {
        this.contractSubject = contractSubject;
    }

    public BigDecimal getPaymentBalance() {
        return paymentBalance;
    }

    public void setPaymentBalance(BigDecimal paymentBalance) {
        this.paymentBalance = paymentBalance;
    }

    public BigDecimal getNmck() {
        return nmck;
    }

    public void setNmck(BigDecimal nmck) {
        this.nmck = nmck;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Date getScheduleDatePlan() {
        return scheduleDatePlan;
    }

    public void setScheduleDatePlan(Date scheduleDatePlan) {
        this.scheduleDatePlan = scheduleDatePlan;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Date getNotificationDatePlan() {
        return notificationDatePlan;
    }

    public void setNotificationDatePlan(Date notificationDatePlan) {
        this.notificationDatePlan = notificationDatePlan;
    }

    public Date getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(Date auctionDate) {
        this.auctionDate = auctionDate;
    }

    public Date getAuctionDatePlan() {
        return auctionDatePlan;
    }

    public void setAuctionDatePlan(Date auctionDatePlan) {
        this.auctionDatePlan = auctionDatePlan;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public Date getContractDatePlan() {
        return contractDatePlan;
    }

    public void setContractDatePlan(Date contractDatePlan) {
        this.contractDatePlan = contractDatePlan;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateEndPlan() {
        return dateEndPlan;
    }

    public void setDateEndPlan(Date dateEndPlan) {
        this.dateEndPlan = dateEndPlan;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getConclusionOfEstimatedCostDate() {
        return conclusionOfEstimatedCostDate;
    }

    public void setConclusionOfEstimatedCostDate(Date conclusionOfEstimatedCostDate) {
        this.conclusionOfEstimatedCostDate = conclusionOfEstimatedCostDate;
    }

    public String getConclusionOfEstimatedCostNumber() {
        return conclusionOfEstimatedCostNumber;
    }

    public void setConclusionOfEstimatedCostNumber(String conclusionOfEstimatedCostNumber) {
        this.conclusionOfEstimatedCostNumber = conclusionOfEstimatedCostNumber;
    }

    public String getConclusionOfEstimatedCostDetails() {
        return conclusionOfEstimatedCostDetails;
    }

    public void setConclusionOfEstimatedCostDetails(String conclusionOfEstimatedCostDetails) {
        this.conclusionOfEstimatedCostDetails = conclusionOfEstimatedCostDetails;
    }

    public Date getConclusionOfProjectDocumentationDate() {
        return conclusionOfProjectDocumentationDate;
    }

    public void setConclusionOfProjectDocumentationDate(Date conclusionOfProjectDocumentationDate) {
        this.conclusionOfProjectDocumentationDate = conclusionOfProjectDocumentationDate;
    }

    public String getConclusionOfProjectDocumentationNumber() {
        return conclusionOfProjectDocumentationNumber;
    }

    public void setConclusionOfProjectDocumentationNumber(String conclusionOfProjectDocumentationNumber) {
        this.conclusionOfProjectDocumentationNumber = conclusionOfProjectDocumentationNumber;
    }

    public String getConclusionOfProjectDocumentationDetails() {
        return conclusionOfProjectDocumentationDetails;
    }

    public void setConclusionOfProjectDocumentationDetails(String conclusionOfProjectDocumentationDetails) {
        this.conclusionOfProjectDocumentationDetails = conclusionOfProjectDocumentationDetails;
    }

    public Date getConclusionOfEcologicalExpertiseDate() {
        return conclusionOfEcologicalExpertiseDate;
    }

    public void setConclusionOfEcologicalExpertiseDate(Date conclusionOfEcologicalExpertiseDate) {
        this.conclusionOfEcologicalExpertiseDate = conclusionOfEcologicalExpertiseDate;
    }

    public String getConclusionOfEcologicalExpertiseNumber() {
        return conclusionOfEcologicalExpertiseNumber;
    }

    public void setConclusionOfEcologicalExpertiseNumber(String conclusionOfEcologicalExpertiseNumber) {
        this.conclusionOfEcologicalExpertiseNumber = conclusionOfEcologicalExpertiseNumber;
    }

    public String getConclusionOfEcologicalExpertiseDetails() {
        return conclusionOfEcologicalExpertiseDetails;
    }

    public void setConclusionOfEcologicalExpertiseDetails(String conclusionOfEcologicalExpertiseDetails) {
        this.conclusionOfEcologicalExpertiseDetails = conclusionOfEcologicalExpertiseDetails;
    }

    public ContractDto getContract() {
        return contract;
    }

    public void setContract(ContractDto contract) {
        this.contract = contract;
    }

    public BigDecimal getEconomy() {
        return economy;
    }

    public void setEconomy(BigDecimal economy) {
        this.economy = economy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondColorlight that = (SecondColorlight) o;
        return Objects.equals(workPackageId, that.workPackageId) && Objects.equals(contractId, that.contractId) && Objects.equals(year, that.year) && Objects.equals(workPackageName, that.workPackageName) && Objects.equals(paymentBalance, that.paymentBalance) && Objects.equals(contractSubject, that.contractSubject) && Objects.equals(nmck, that.nmck) && Objects.equals(price, that.price) && Objects.equals(spent, that.spent) && Objects.equals(scheduleDate, that.scheduleDate) && Objects.equals(scheduleDatePlan, that.scheduleDatePlan) && Objects.equals(notificationDate, that.notificationDate) && Objects.equals(notificationDatePlan, that.notificationDatePlan) && Objects.equals(auctionDate, that.auctionDate) && Objects.equals(auctionDatePlan, that.auctionDatePlan) && Objects.equals(contractDate, that.contractDate) && Objects.equals(contractDatePlan, that.contractDatePlan) && Objects.equals(dateEnd, that.dateEnd) && Objects.equals(dateEndPlan, that.dateEndPlan) && Objects.equals(note, that.note) && Objects.equals(conclusionOfEstimatedCostDate, that.conclusionOfEstimatedCostDate) && Objects.equals(conclusionOfEstimatedCostNumber, that.conclusionOfEstimatedCostNumber) && Objects.equals(conclusionOfEstimatedCostDetails, that.conclusionOfEstimatedCostDetails) && Objects.equals(conclusionOfProjectDocumentationDate, that.conclusionOfProjectDocumentationDate) && Objects.equals(conclusionOfProjectDocumentationNumber, that.conclusionOfProjectDocumentationNumber) && Objects.equals(conclusionOfProjectDocumentationDetails, that.conclusionOfProjectDocumentationDetails) && Objects.equals(conclusionOfEcologicalExpertiseDate, that.conclusionOfEcologicalExpertiseDate) && Objects.equals(conclusionOfEcologicalExpertiseNumber, that.conclusionOfEcologicalExpertiseNumber) && Objects.equals(conclusionOfEcologicalExpertiseDetails, that.conclusionOfEcologicalExpertiseDetails) && Objects.equals(contract, that.contract) && Objects.equals(economy, that.economy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workPackageId, contractId, year, workPackageName, paymentBalance, contractSubject, nmck, price, spent, scheduleDate, scheduleDatePlan, notificationDate, notificationDatePlan, auctionDate, auctionDatePlan, contractDate, contractDatePlan, dateEnd, dateEndPlan, note, conclusionOfEstimatedCostDate, conclusionOfEstimatedCostNumber, conclusionOfEstimatedCostDetails, conclusionOfProjectDocumentationDate, conclusionOfProjectDocumentationNumber, conclusionOfProjectDocumentationDetails, conclusionOfEcologicalExpertiseDate, conclusionOfEcologicalExpertiseNumber, conclusionOfEcologicalExpertiseDetails, contract, economy);
    }
}
