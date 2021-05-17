package ru.sibdigital.jopsd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cost_entries", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CostEntry {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "COST_ENTRIES_GEN", sequenceName = "cost_entries_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COST_ENTRIES_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    private Long userId;
    private Long projectId;
    private Long workPackageId;
    private Long costTypeId;
    private Double units;
    private Date spentOn;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private String comments;
    private Boolean blocked;
    private Double overriddenCosts;
    private BigDecimal costs;
    private Long rateId;
    private Integer tyear;
    private Integer tmonth;
    private Integer tweek;
    private Double recordedLiability;
    private Integer planYear;


    @Basic
    @Column(name = "project_id")
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }


    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "work_package_id")
    public Long getWorkPackageId() {
        return workPackageId;
    }

    public void setWorkPackageId(Long workPackageId) {
        this.workPackageId = workPackageId;
    }

    @Basic
    @Column(name = "cost_type_id")
    public Long getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(Long costTypeId) {
        this.costTypeId = costTypeId;
    }

    @Basic
    @Column(name = "units")
    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    @Basic
    @Column(name = "spent_on")
    public Date getSpentOn() {
        return spentOn;
    }

    public void setSpentOn(Date spentOn) {
        this.spentOn = spentOn;
    }

    @Basic
    @Column(name = "created_on")
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Basic
    @Column(name = "updated_on")
    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Basic
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "blocked")
    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Basic
    @Column(name = "overridden_costs")
    public Double getOverriddenCosts() {
        return overriddenCosts;
    }

    public void setOverriddenCosts(Double overriddenCosts) {
        this.overriddenCosts = overriddenCosts;
    }

    @Basic
    @Column(name = "costs")
    public BigDecimal getCosts() {
        return costs;
    }

    public void setCosts(BigDecimal costs) {
        this.costs = costs;
    }

    @Basic
    @Column(name = "rate_id")
    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    @Basic
    @Column(name = "tyear")
    public Integer getTyear() {
        return tyear;
    }

    public void setTyear(Integer tyear) {
        this.tyear = tyear;
    }

    @Basic
    @Column(name = "tmonth")
    public Integer getTmonth() {
        return tmonth;
    }

    public void setTmonth(Integer tmonth) {
        this.tmonth = tmonth;
    }

    @Basic
    @Column(name = "tweek")
    public Integer getTweek() {
        return tweek;
    }

    public void setTweek(Integer tweek) {
        this.tweek = tweek;
    }

    @Basic
    @Column(name = "recorded_liability")
    public Double getRecordedLiability() {
        return recordedLiability;
    }

    public void setRecordedLiability(Double recordedLiability) {
        this.recordedLiability = recordedLiability;
    }

    @Basic
    @Column(name = "plan_year")
    public Integer getPlanYear() {
        return planYear;
    }

    public void setPlanYear(Integer planYear) {
        this.planYear = planYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostEntry costEntry = (CostEntry) o;
        return Objects.equals(id, costEntry.id) && Objects.equals(userId, costEntry.userId) && Objects.equals(projectId, costEntry.projectId) && Objects.equals(workPackageId, costEntry.workPackageId) && Objects.equals(costTypeId, costEntry.costTypeId) && Objects.equals(units, costEntry.units) && Objects.equals(spentOn, costEntry.spentOn) && Objects.equals(createdOn, costEntry.createdOn) && Objects.equals(updatedOn, costEntry.updatedOn) && Objects.equals(comments, costEntry.comments) && Objects.equals(blocked, costEntry.blocked) && Objects.equals(overriddenCosts, costEntry.overriddenCosts) && Objects.equals(costs, costEntry.costs) && Objects.equals(rateId, costEntry.rateId) && Objects.equals(tyear, costEntry.tyear) && Objects.equals(tmonth, costEntry.tmonth) && Objects.equals(tweek, costEntry.tweek) && Objects.equals(recordedLiability, costEntry.recordedLiability) && Objects.equals(planYear, costEntry.planYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, projectId, workPackageId, costTypeId, units, spentOn, createdOn, updatedOn, comments, blocked, overriddenCosts, costs, rateId, tyear, tmonth, tweek, recordedLiability, planYear);
    }
}
