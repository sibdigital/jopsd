package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "time_entry_journals", indexes = {
        @Index(name = "index_time_entry_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class TimeEntryJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "work_package_id")
    private Long workPackageId;

    @Column(name = "hours", nullable = false)
    private Double hours;

    @Column(name = "comments")
    private String comments;

    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    @Column(name = "spent_on", nullable = false)
    private LocalDateTime spentOn;

    @Column(name = "tyear", nullable = false)
    private Integer tyear;

    @Column(name = "tmonth", nullable = false)
    private Integer tmonth;

    @Column(name = "tweek", nullable = false)
    private Integer tweek;

    @Column(name = "overridden_costs", precision = 15, scale = 2)
    private BigDecimal overriddenCosts;

    @Column(name = "costs", precision = 15, scale = 2)
    private BigDecimal costs;

    @Column(name = "rate_id")
    private Integer rateId;

    public Integer getRateId() {
        return rateId;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
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

    public LocalDateTime getSpentOn() {
        return spentOn;
    }

    public void setSpentOn(LocalDateTime spentOn) {
        this.spentOn = spentOn;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Long getWorkPackageId() {
        return workPackageId;
    }

    public void setWorkPackageId(Long workPackageId) {
        this.workPackageId = workPackageId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}