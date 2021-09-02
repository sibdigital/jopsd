package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "cost_entries")
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CostEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "project_id")
    private Project project;
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "work_package_id")
    private WorkPackage workPackage;
    public WorkPackage getWorkPackage() {
        return workPackage;
    }
    public void setWorkPackage(WorkPackage workPackage) {
        this.workPackage = workPackage;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "cost_type_id")
    private CostType costType;
    public CostType getCostType() {
        return costType;
    }
    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "rate_id")
    private Rate rate;
    public Rate getRate() {
        return rate;
    }
    public void setRate(Rate rate) {
        this.rate = rate;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "cost_object_id")
    private CostObject costObject;
    public CostObject getCostObject() {
        return costObject;
    }
    public void setCostObject(CostObject costObject) {
        this.costObject = costObject;
    }

    @Column(name = "units", nullable = false)
    private Double units;

    @Column(name = "spent_on", nullable = false)
    private LocalDateTime spentOn;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "updated_on", nullable = false)
    private Timestamp updatedOn;

    @Column(name = "comments", nullable = false)
    private String comments;

    @Column(name = "blocked", nullable = false)
    private Boolean blocked = false;

    @Column(name = "overridden_costs", precision = 15, scale = 4)
    private BigDecimal overriddenCosts;

    @Column(name = "costs", precision = 15, scale = 4)
    private BigDecimal costs;

    @Column(name = "tyear", nullable = false)
    private Integer tyear;

    @Column(name = "tmonth", nullable = false)
    private Integer tmonth;

    @Column(name = "tweek", nullable = false)
    private Integer tweek;

    @Column(name = "recorded_liability", precision = 131089)
    private BigDecimal recordedLiability;

    @Column(name = "plan_year")
    private Integer planYear;

    public Integer getPlanYear() {
        return planYear;
    }

    public void setPlanYear(Integer planYear) {
        this.planYear = planYear;
    }

    public BigDecimal getRecordedLiability() {
        return recordedLiability;
    }

    public void setRecordedLiability(BigDecimal recordedLiability) {
        this.recordedLiability = recordedLiability;
    }

    public Integer getTweek() {
        return tweek;
    }

    public void setTweek(Integer tweek) {
        this.tweek = tweek;
    }

    public Integer getTmonth() {
        return tmonth;
    }

    public void setTmonth(Integer tmonth) {
        this.tmonth = tmonth;
    }

    public Integer getTyear() {
        return tyear;
    }

    public void setTyear(Integer tyear) {
        this.tyear = tyear;
    }

    public BigDecimal getCosts() {
        return costs;
    }

    public void setCosts(BigDecimal costs) {
        this.costs = costs;
    }

    public BigDecimal getOverriddenCosts() {
        return overriddenCosts;
    }

    public void setOverriddenCosts(BigDecimal overriddenCosts) {
        this.overriddenCosts = overriddenCosts;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getSpentOn() {
        return spentOn;
    }

    public void setSpentOn(LocalDateTime spentOn) {
        this.spentOn = spentOn;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}