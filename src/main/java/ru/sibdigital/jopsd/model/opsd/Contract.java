package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "contracts")
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "contract_subject")
    private String contractSubject;

    @Column(name = "contract_date")
    private LocalDateTime contractDate;

    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "executor")
    private String executor;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "contract_num", length = 30)
    private String contractNum;

    @Column(name = "is_approve")
    private Boolean isApprove;

    @Column(name = "eis_href")
    private String eisHref;

    @Column(name = "name")
    private String name;

    @Column(name = "sposob")
    private String sposob;

    @Column(name = "gos_zakaz")
    private String gosZakaz;

    @Column(name = "date_begin")
    private LocalDateTime dateBegin;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @Column(name = "etaps")
    private String etaps;

    @Column(name = "auction_date")
    private LocalDateTime auctionDate;

    @Column(name = "schedule_date")
    private LocalDateTime scheduleDate;


    @Column(name = "nmck", precision = 15, scale = 2)
    private BigDecimal nmck;

    @Column(name = "schedule_date_plan")
    private LocalDateTime scheduleDatePlan;

    @Column(name = "notification_date_plan")
    private LocalDateTime notificationDatePlan;

    @Column(name = "notification_date")
    private LocalDateTime notificationDate;

    @Column(name = "auction_date_plan")
    private LocalDateTime auctionDatePlan;

    @Column(name = "contract_date_plan")
    private LocalDateTime contractDatePlan;

    @Column(name = "date_end_plan")
    private LocalDateTime dateEndPlan;

    @Column(name = "note")
    private String note;

    @Column(name = "conclusion_of_estimated_cost_details")
    private String conclusionOfEstimatedCostDetails;

    @Column(name = "conclusion_of_estimated_cost_number")
    private String conclusionOfEstimatedCostNumber;

    @Column(name = "conclusion_of_estimated_cost_date")
    private LocalDateTime conclusionOfEstimatedCostDate;

    @Column(name = "conclusion_of_project_documentation_details")
    private String conclusionOfProjectDocumentationDetails;

    @Column(name = "conclusion_of_project_documentation_number")
    private String conclusionOfProjectDocumentationNumber;

    @Column(name = "conclusion_of_project_documentation_date")
    private LocalDateTime conclusionOfProjectDocumentationDate;

    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }


    public LocalDateTime getConclusionOfProjectDocumentationDate() {
        return conclusionOfProjectDocumentationDate;
    }

    public void setConclusionOfProjectDocumentationDate(LocalDateTime conclusionOfProjectDocumentationDate) {
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

    public LocalDateTime getConclusionOfEstimatedCostDate() {
        return conclusionOfEstimatedCostDate;
    }

    public void setConclusionOfEstimatedCostDate(LocalDateTime conclusionOfEstimatedCostDate) {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDateEndPlan() {
        return dateEndPlan;
    }

    public void setDateEndPlan(LocalDateTime dateEndPlan) {
        this.dateEndPlan = dateEndPlan;
    }

    public LocalDateTime getContractDatePlan() {
        return contractDatePlan;
    }

    public void setContractDatePlan(LocalDateTime contractDatePlan) {
        this.contractDatePlan = contractDatePlan;
    }

    public LocalDateTime getAuctionDatePlan() {
        return auctionDatePlan;
    }

    public void setAuctionDatePlan(LocalDateTime auctionDatePlan) {
        this.auctionDatePlan = auctionDatePlan;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public LocalDateTime getNotificationDatePlan() {
        return notificationDatePlan;
    }

    public void setNotificationDatePlan(LocalDateTime notificationDatePlan) {
        this.notificationDatePlan = notificationDatePlan;
    }

    public LocalDateTime getScheduleDatePlan() {
        return scheduleDatePlan;
    }

    public void setScheduleDatePlan(LocalDateTime scheduleDatePlan) {
        this.scheduleDatePlan = scheduleDatePlan;
    }

    public BigDecimal getNmck() {
        return nmck;
    }

    public void setNmck(BigDecimal nmck) {
        this.nmck = nmck;
    }

    public LocalDateTime getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDateTime scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public LocalDateTime getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(LocalDateTime auctionDate) {
        this.auctionDate = auctionDate;
    }

    public String getEtaps() {
        return etaps;
    }

    public void setEtaps(String etaps) {
        this.etaps = etaps;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDateTime getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDateTime dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getGosZakaz() {
        return gosZakaz;
    }

    public void setGosZakaz(String gosZakaz) {
        this.gosZakaz = gosZakaz;
    }

    public String getSposob() {
        return sposob;
    }

    public void setSposob(String sposob) {
        this.sposob = sposob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEisHref() {
        return eisHref;
    }

    public void setEisHref(String eisHref) {
        this.eisHref = eisHref;
    }

    public Boolean getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Boolean isApprove) {
        this.isApprove = isApprove;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDateTime contractDate) {
        this.contractDate = contractDate;
    }

    public String getContractSubject() {
        return contractSubject;
    }

    public void setContractSubject(String contractSubject) {
        this.contractSubject = contractSubject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}