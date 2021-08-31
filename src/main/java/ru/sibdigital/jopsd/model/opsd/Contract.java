package ru.sibdigital.jopsd.model.opsd;

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

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "auction_date")
    private LocalDateTime auctionDate;

    @Column(name = "schedule_date")
    private LocalDateTime scheduleDate;

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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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