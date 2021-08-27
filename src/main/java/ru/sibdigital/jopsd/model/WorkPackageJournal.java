package ru.sibdigital.jopsd.model;

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

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Lob
    @Column(name = "subject", nullable = false)
    private String subject;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "status_id", nullable = false)
    private Long statusId;

    @Column(name = "assigned_to_id")
    private Long assignedToId;

    @Column(name = "priority_id", nullable = false)
    private Integer priorityId;

    @Column(name = "fixed_version_id")
    private Long fixedVersionId;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "done_ratio", nullable = false)
    private Integer doneRatio;

    @Column(name = "estimated_hours")
    private Double estimatedHours;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "responsible_id")
    private Long responsibleId;

    @Column(name = "cost_object_id")
    private Long costObjectId;

    @Column(name = "story_points")
    private Integer storyPoints;

    @Column(name = "remaining_hours")
    private Double remainingHours;

    @Column(name = "contract_id")
    private Long contractId;

    @Column(name = "result_agreed")
    private Boolean resultAgreed;

    @Column(name = "organization_id")
    private Long organizationId;

    @Lob
    @Column(name = "sed_href")
    private String sedHref;

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "arbitary_object_id")
    private Long arbitaryObjectId;

    @Column(name = "plan_num_pp", length = 10)
    private String planNumPp;

    @Column(name = "required_doc_type_id")
    private Long requiredDocTypeId;

    @Column(name = "raion_id")
    private Long raionId;

    @Column(name = "work_package_id")
    private Long workPackageId;

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

    @Column(name = "control_level_id")
    private Integer controlLevelId;

    public Integer getControlLevelId() {
        return controlLevelId;
    }

    public void setControlLevelId(Integer controlLevelId) {
        this.controlLevelId = controlLevelId;
    }

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

    public Long getWorkPackageId() {
        return workPackageId;
    }

    public void setWorkPackageId(Long workPackageId) {
        this.workPackageId = workPackageId;
    }

    public Long getRaionId() {
        return raionId;
    }

    public void setRaionId(Long raionId) {
        this.raionId = raionId;
    }

    public Long getRequiredDocTypeId() {
        return requiredDocTypeId;
    }

    public void setRequiredDocTypeId(Long requiredDocTypeId) {
        this.requiredDocTypeId = requiredDocTypeId;
    }

    public String getPlanNumPp() {
        return planNumPp;
    }

    public void setPlanNumPp(String planNumPp) {
        this.planNumPp = planNumPp;
    }

    public Long getArbitaryObjectId() {
        return arbitaryObjectId;
    }

    public void setArbitaryObjectId(Long arbitaryObjectId) {
        this.arbitaryObjectId = arbitaryObjectId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getSedHref() {
        return sedHref;
    }

    public void setSedHref(String sedHref) {
        this.sedHref = sedHref;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Boolean getResultAgreed() {
        return resultAgreed;
    }

    public void setResultAgreed(Boolean resultAgreed) {
        this.resultAgreed = resultAgreed;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
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

    public Long getCostObjectId() {
        return costObjectId;
    }

    public void setCostObjectId(Long costObjectId) {
        this.costObjectId = costObjectId;
    }

    public Long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Long responsibleId) {
        this.responsibleId = responsibleId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getFixedVersionId() {
        return fixedVersionId;
    }

    public void setFixedVersionId(Long fixedVersionId) {
        this.fixedVersionId = fixedVersionId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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