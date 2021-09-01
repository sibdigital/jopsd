package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "time_entries", indexes = {
        @Index(name = "index_time_entries_on_user_id", columnList = "user_id"),
        @Index(name = "time_entries_project_id", columnList = "project_id"),
        @Index(name = "index_time_entries_on_activity_id", columnList = "activity_id"),
        @Index(name = "index_time_entries_on_created_on", columnList = "created_on"),
        @Index(name = "index_time_entries_on_project_id_and_updated_on", columnList = "project_id, updated_on"),
        @Index(name = "time_entries_issue_id", columnList = "work_package_id")
})
@Entity
public class TimeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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
    @JoinColumn(name = "activity_id", nullable = false)
    private Enumeration activity;
    public Enumeration getActivity() {
        return activity;
    }
    public void setActivity(Enumeration activity) {
        this.activity = activity;
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

    @Column(name = "hours", nullable = false)
    private Double hours;

    @Column(name = "comments")
    private String comments;

    @Column(name = "spent_on", nullable = false)
    private LocalDateTime spentOn;

    @Column(name = "tyear", nullable = false)
    private Integer tyear;

    @Column(name = "tmonth", nullable = false)
    private Integer tmonth;

    @Column(name = "tweek", nullable = false)
    private Integer tweek;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "updated_on", nullable = false)
    private Timestamp updatedOn;

    @Column(name = "overridden_costs", precision = 15, scale = 4)
    private BigDecimal overriddenCosts;

    @Column(name = "costs", precision = 15, scale = 4)
    private BigDecimal costs;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}