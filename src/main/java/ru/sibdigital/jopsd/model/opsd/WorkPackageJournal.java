package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "work_package_journals", indexes = {
        @Index(name = "work_package_journal_on_burndown_attributes", columnList = "fixed_version_id, status_id, project_id, type_id"),
        @Index(name = "index_work_package_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class WorkPackageJournal {
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
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
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
    @JoinColumn(name = "category_id")
    private Category category;
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "status_id")
    private Status status;
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;
    public User getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "priority_id")
    private Enumeration priority;
    public Enumeration getPriority() {
        return priority;
    }
    public void setPriority(Enumeration priority) {
        this.priority = priority;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "fixed_version_id")
    private VersionModel fixedVersion;
    public VersionModel getFixedVersion() {
        return fixedVersion;
    }
    public void setFixedVersion(VersionModel fixedVersion) {
        this.fixedVersion = fixedVersion;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "author_id")
    private User author;
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "parent_id")
    private WorkPackage parent;
    public WorkPackage getParent() {
        return parent;
    }
    public void setParent(WorkPackage parent) {
        this.parent = parent;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "responsible_id")
    private User responsible;
    public User getResponsible() {
        return responsible;
    }
    public void setResponsible(User responsible) {
        this.responsible = responsible;
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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "organization_id")
    private Organization organization;
    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "contract_id")
    private Contract contract;
    public Contract getContract() {
        return contract;
    }
    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "target_id")
    private Target target;
    public Target getTarget() {
        return target;
    }
    public void setTarget(Target target) {
        this.target = target;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "arbitary_object_id")
    private ArbitaryObject arbitaryObject;
    public ArbitaryObject getArbitaryObject() {
        return arbitaryObject;
    }
    public void setArbitaryObject(ArbitaryObject arbitaryObject) {
        this.arbitaryObject = arbitaryObject;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "required_doc_type_id")
    private Enumeration requiredDocType;
    public Enumeration getRequiredDocType() {
        return requiredDocType;
    }
    public void setRequiredDocType(Enumeration requiredDocType) {
        this.requiredDocType = requiredDocType;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "control_level_id")
    private ControlLevel controlLevel;
    public ControlLevel getControlLevel() {
        return controlLevel;
    }
    public void setControlLevel(ControlLevel controlLevel) {
        this.controlLevel = controlLevel;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "raion_id")
    private Raion raion;
    public Raion getRaion() {
        return raion;
    }
    public void setRaion(Raion raion) {
        this.raion = raion;
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

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "done_ratio", nullable = false)
    private Integer doneRatio;

    @Column(name = "estimated_hours")
    private Double estimatedHours;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "story_points")
    private Integer storyPoints;

    @Column(name = "remaining_hours")
    private Double remainingHours;

    @Column(name = "result_agreed")
    private Boolean resultAgreed;

    @Column(name = "sed_href")
    private String sedHref;

    @Column(name = "plan_num_pp", length = 10)
    private String planNumPp;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "fact_due_date")
    private Timestamp factDueDate;

    @Column(name = "first_due_date")
    private LocalDateTime firstDueDate;

    @Column(name = "last_due_date")
    private LocalDateTime lastDueDate;

    @Column(name = "first_start_date")
    private LocalDateTime firstStartDate;

    @Column(name = "last_start_date")
    private LocalDateTime lastStartDate;

    public LocalDateTime getLastStartDate() {
        return lastStartDate;
    }

    public void setLastStartDate(LocalDateTime lastStartDate) {
        this.lastStartDate = lastStartDate;
    }

    public LocalDateTime getFirstStartDate() {
        return firstStartDate;
    }

    public void setFirstStartDate(LocalDateTime firstStartDate) {
        this.firstStartDate = firstStartDate;
    }

    public LocalDateTime getLastDueDate() {
        return lastDueDate;
    }

    public void setLastDueDate(LocalDateTime lastDueDate) {
        this.lastDueDate = lastDueDate;
    }

    public LocalDateTime getFirstDueDate() {
        return firstDueDate;
    }

    public void setFirstDueDate(LocalDateTime firstDueDate) {
        this.firstDueDate = firstDueDate;
    }

    public Timestamp getFactDueDate() {
        return factDueDate;
    }

    public void setFactDueDate(Timestamp factDueDate) {
        this.factDueDate = factDueDate;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPlanNumPp() {
        return planNumPp;
    }

    public void setPlanNumPp(String planNumPp) {
        this.planNumPp = planNumPp;
    }

    public String getSedHref() {
        return sedHref;
    }

    public void setSedHref(String sedHref) {
        this.sedHref = sedHref;
    }

    public Boolean getResultAgreed() {
        return resultAgreed;
    }

    public void setResultAgreed(Boolean resultAgreed) {
        this.resultAgreed = resultAgreed;
    }

    public Double getRemainingHours() {
        return remainingHours;
    }

    public void setRemainingHours(Double remainingHours) {
        this.remainingHours = remainingHours;
    }

    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Integer getDoneRatio() {
        return doneRatio;
    }

    public void setDoneRatio(Integer doneRatio) {
        this.doneRatio = doneRatio;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}