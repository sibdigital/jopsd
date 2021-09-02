package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "journal_id")
    private Journal journal;
    public Journal getJournal() {
        return journal;
    }
    public void setJournal(Journal journal) {
        this.journal = journal;
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

    @Column(name = "overridden_costs", precision = 15, scale = 2)
    private BigDecimal overriddenCosts;

    @Column(name = "costs", precision = 15, scale = 2)
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