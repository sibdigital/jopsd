package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

    private Long typeId;
    private Long projectId;
    private String subject;
    private String description;
    private LocalDateTime dueDate;
    private Long categoryId;
    private Long statusId;
    private Long assignedToId;
    private Long priorityId;
    private Long fixedVersionId;
    private Long authorId;
    private Long lockVersion;
    private Long doneRatio;
    private Double estimatedHours;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private LocalDateTime startDate;
    private Long responsibleId;
    private Long costObjectId;
    private Integer position;
    private Integer storyPoints;
    private Double remainingHours;
    private String planType;
    private Long contractId;
    private Boolean resultAgreed;
    private Long organizationId;
    private String sedHref;
    private Long arbitaryObjectId;
    private String planNumPp;
    private Long raionId;
    private Long requiredDocTypeId;
    private Timestamp factDueDate;
    private Timestamp firstDueDate;
    private Timestamp lastDueDate;
    private Timestamp firstStartDate;
    private Timestamp lastStartDate;
    private Long periodId;
    private Long controlLevelId;
    private Long outerId;
    private Long metaId;

    @Basic
    @Column(name = "type_id")
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "project_id")
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

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
    @Column(name = "category_id")
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "status_id")
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "assigned_to_id")
    public Long getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(Long assignedToId) {
        this.assignedToId = assignedToId;
    }

    @Basic
    @Column(name = "priority_id")
    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    @Basic
    @Column(name = "fixed_version_id")
    public Long getFixedVersionId() {
        return fixedVersionId;
    }

    public void setFixedVersionId(Long fixedVersionId) {
        this.fixedVersionId = fixedVersionId;
    }

    @Basic
    @Column(name = "author_id")
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
    @Column(name = "responsible_id")
    public Long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Long responsibleId) {
        this.responsibleId = responsibleId;
    }

    @Basic
    @Column(name = "cost_object_id")
    public Long getCostObjectId() {
        return costObjectId;
    }

    public void setCostObjectId(Long costObjectId) {
        this.costObjectId = costObjectId;
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
    @Column(name = "contract_id")
    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
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
    @Column(name = "organization_id")
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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
    @Column(name = "arbitary_object_id")
    public Long getArbitaryObjectId() {
        return arbitaryObjectId;
    }

    public void setArbitaryObjectId(Long arbitaryObjectId) {
        this.arbitaryObjectId = arbitaryObjectId;
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
    @Column(name = "raion_id")
    public Long getRaionId() {
        return raionId;
    }

    public void setRaionId(Long raionId) {
        this.raionId = raionId;
    }

    @Basic
    @Column(name = "required_doc_type_id")
    public Long getRequiredDocTypeId() {
        return requiredDocTypeId;
    }

    public void setRequiredDocTypeId(Long requiredDocTypeId) {
        this.requiredDocTypeId = requiredDocTypeId;
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
    @Column(name = "period_id")
    public Long getPeriodId() {
        return periodId;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    @Basic
    @Column(name = "control_level_id")
    public Long getControlLevelId() {
        return controlLevelId;
    }

    public void setControlLevelId(Long controlLevelId) {
        this.controlLevelId = controlLevelId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkPackage that = (WorkPackage) o;
        return Objects.equals(id, that.id) && Objects.equals(typeId, that.typeId) && Objects.equals(projectId, that.projectId) && Objects.equals(subject, that.subject) && Objects.equals(description, that.description) && Objects.equals(dueDate, that.dueDate) && Objects.equals(categoryId, that.categoryId) && Objects.equals(statusId, that.statusId) && Objects.equals(assignedToId, that.assignedToId) && Objects.equals(priorityId, that.priorityId) && Objects.equals(fixedVersionId, that.fixedVersionId) && Objects.equals(authorId, that.authorId) && Objects.equals(lockVersion, that.lockVersion) && Objects.equals(doneRatio, that.doneRatio) && Objects.equals(estimatedHours, that.estimatedHours) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(startDate, that.startDate) && Objects.equals(responsibleId, that.responsibleId) && Objects.equals(costObjectId, that.costObjectId) && Objects.equals(position, that.position) && Objects.equals(storyPoints, that.storyPoints) && Objects.equals(remainingHours, that.remainingHours) && Objects.equals(planType, that.planType) && Objects.equals(contractId, that.contractId) && Objects.equals(resultAgreed, that.resultAgreed) && Objects.equals(organizationId, that.organizationId) && Objects.equals(sedHref, that.sedHref) && Objects.equals(arbitaryObjectId, that.arbitaryObjectId) && Objects.equals(planNumPp, that.planNumPp) && Objects.equals(raionId, that.raionId) && Objects.equals(requiredDocTypeId, that.requiredDocTypeId) && Objects.equals(factDueDate, that.factDueDate) && Objects.equals(firstDueDate, that.firstDueDate) && Objects.equals(lastDueDate, that.lastDueDate) && Objects.equals(firstStartDate, that.firstStartDate) && Objects.equals(lastStartDate, that.lastStartDate) && Objects.equals(periodId, that.periodId) && Objects.equals(controlLevelId, that.controlLevelId) && Objects.equals(outerId, that.outerId) && Objects.equals(metaId, that.metaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeId, projectId, subject, description, dueDate, categoryId, statusId, assignedToId, priorityId, fixedVersionId, authorId, lockVersion, doneRatio, estimatedHours, createdAt, updatedAt, startDate, responsibleId, costObjectId, position, storyPoints, remainingHours, planType, contractId, resultAgreed, organizationId, sedHref, arbitaryObjectId, planNumPp, raionId, requiredDocTypeId, factDueDate, firstDueDate, lastDueDate, firstStartDate, lastStartDate, periodId, controlLevelId, outerId, metaId);
    }
}
