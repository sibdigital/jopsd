package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "work_packages", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WorkPackage {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "WORK_PACKAGES_GEN", sequenceName = "work_packages_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORK_PACKAGES_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

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
    @JoinColumn(name = "period_id")
    private Enumeration period;
    public Enumeration getPeriod() {
        return period;
    }
    public void setPeriod(Enumeration period) {
        this.period = period;
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

    private String subject;
    private String description;
    private LocalDateTime dueDate;
    private Long lockVersion;
    private Long doneRatio;
    private Double estimatedHours;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private LocalDateTime startDate;
    private Integer position;
    private Integer storyPoints;
    private Double remainingHours;
    private String planType;
    private Boolean resultAgreed;
    private String sedHref;
    private String planNumPp;
    private Timestamp factDueDate;
    private Timestamp firstDueDate;
    private Timestamp lastDueDate;
    private Timestamp firstStartDate;
    private Timestamp lastStartDate;
    private Long outerId;
    private Long metaId;


    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "due_date")
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "lock_version")
    public Long getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Long lockVersion) {
        this.lockVersion = lockVersion;
    }

    @Basic
    @Column(name = "done_ratio")
    public Long getDoneRatio() {
        return doneRatio;
    }

    public void setDoneRatio(Long doneRatio) {
        this.doneRatio = doneRatio;
    }

    @Basic
    @Column(name = "estimated_hours")
    public Double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "start_date")
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    @Column(name = "story_points")
    public Integer getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(Integer storyPoints) {
        this.storyPoints = storyPoints;
    }

    @Basic
    @Column(name = "remaining_hours")
    public Double getRemainingHours() {
        return remainingHours;
    }

    public void setRemainingHours(Double remainingHours) {
        this.remainingHours = remainingHours;
    }

    @Basic
    @Column(name = "plan_type")
    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    @Basic
    @Column(name = "result_agreed")
    public Boolean getResultAgreed() {
        return resultAgreed;
    }

    public void setResultAgreed(Boolean resultAgreed) {
        this.resultAgreed = resultAgreed;
    }

    @Basic
    @Column(name = "sed_href")
    public String getSedHref() {
        return sedHref;
    }

    public void setSedHref(String sedHref) {
        this.sedHref = sedHref;
    }

    @Basic
    @Column(name = "plan_num_pp")
    public String getPlanNumPp() {
        return planNumPp;
    }

    public void setPlanNumPp(String planNumPp) {
        this.planNumPp = planNumPp;
    }

    @Basic
    @Column(name = "fact_due_date")
    public Timestamp getFactDueDate() {
        return factDueDate;
    }

    public void setFactDueDate(Timestamp factDueDate) {
        this.factDueDate = factDueDate;
    }

    @Basic
    @Column(name = "first_due_date")
    public Timestamp getFirstDueDate() {
        return firstDueDate;
    }

    public void setFirstDueDate(Timestamp firstDueDate) {
        this.firstDueDate = firstDueDate;
    }

    @Basic
    @Column(name = "last_due_date")
    public Timestamp getLastDueDate() {
        return lastDueDate;
    }

    public void setLastDueDate(Timestamp lastDueDate) {
        this.lastDueDate = lastDueDate;
    }

    @Basic
    @Column(name = "first_start_date")
    public Timestamp getFirstStartDate() {
        return firstStartDate;
    }

    public void setFirstStartDate(Timestamp firstStartDate) {
        this.firstStartDate = firstStartDate;
    }

    @Basic
    @Column(name = "last_start_date")
    public Timestamp getLastStartDate() {
        return lastStartDate;
    }

    public void setLastStartDate(Timestamp lastStartDate) {
        this.lastStartDate = lastStartDate;
    }

    @Basic
    @Column(name = "outer_id")
    public Long getOuterId() {
        return outerId;
    }

    public void setOuterId(Long outerId) {
        this.outerId = outerId;
    }

    @Basic
    @Column(name = "meta_id")
    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }
}
