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
@Table(name = "targets", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Target {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "TARGETS_GEN", sequenceName = "targets_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TARGETS_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    private Long statusId;
    private String name;
    private Long typeId;
    private String unit;
    private Double basicValue;
    private Double planValue;
    private String comment;
    private Long projectId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isApprove;
    private Long parentId;
    private Long measureUnitId;
    private Boolean isAdditional;
    private String type;
    private LocalDateTime basicDate;
    private LocalDateTime planDate;
    private String nationalProjectGoal;
    private String nationalProjectResult;
    private String nationalProjectCharact;
    private LocalDateTime resultDueDate;
    private Long resultAssigned;
    private Long metaId;

    @Basic
    @Column(name = "status_id")
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type_id")
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "basic_value")
    public Double getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(Double basicValue) {
        this.basicValue = basicValue;
    }

    @Basic
    @Column(name = "plan_value")
    public Double getPlanValue() {
        return planValue;
    }

    public void setPlanValue(Double planValue) {
        this.planValue = planValue;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    @Column(name = "is_approve")
    public Boolean getApprove() {
        return isApprove;
    }

    public void setApprove(Boolean approve) {
        isApprove = approve;
    }

    @Basic
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "measure_unit_id")
    public Long getMeasureUnitId() {
        return measureUnitId;
    }

    public void setMeasureUnitId(Long measureUnitId) {
        this.measureUnitId = measureUnitId;
    }

    @Basic
    @Column(name = "is_additional")
    public Boolean getAdditional() {
        return isAdditional;
    }

    public void setAdditional(Boolean additional) {
        isAdditional = additional;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "basic_date")
    public LocalDateTime getBasicDate() {
        return basicDate;
    }

    public void setBasicDate(LocalDateTime basicDate) {
        this.basicDate = basicDate;
    }

    @Basic
    @Column(name = "plan_date")
    public LocalDateTime getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDateTime planDate) {
        this.planDate = planDate;
    }

    @Basic
    @Column(name = "national_project_goal")
    public String getNationalProjectGoal() {
        return nationalProjectGoal;
    }

    public void setNationalProjectGoal(String nationalProjectGoal) {
        this.nationalProjectGoal = nationalProjectGoal;
    }

    @Basic
    @Column(name = "national_project_result")
    public String getNationalProjectResult() {
        return nationalProjectResult;
    }

    public void setNationalProjectResult(String nationalProjectResult) {
        this.nationalProjectResult = nationalProjectResult;
    }

    @Basic
    @Column(name = "national_project_charact")
    public String getNationalProjectCharact() {
        return nationalProjectCharact;
    }

    public void setNationalProjectCharact(String nationalProjectCharact) {
        this.nationalProjectCharact = nationalProjectCharact;
    }

    @Basic
    @Column(name = "result_due_date")
    public LocalDateTime getResultDueDate() {
        return resultDueDate;
    }

    public void setResultDueDate(LocalDateTime resultDueDate) {
        this.resultDueDate = resultDueDate;
    }

    @Basic
    @Column(name = "result_assigned")
    public Long getResultAssigned() {
        return resultAssigned;
    }

    public void setResultAssigned(Long resultAssigned) {
        this.resultAssigned = resultAssigned;
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
        Target target = (Target) o;
        return Objects.equals(id, target.id) && Objects.equals(statusId, target.statusId) && Objects.equals(name, target.name) && Objects.equals(typeId, target.typeId) && Objects.equals(unit, target.unit) && Objects.equals(basicValue, target.basicValue) && Objects.equals(planValue, target.planValue) && Objects.equals(comment, target.comment) && Objects.equals(projectId, target.projectId) && Objects.equals(createdAt, target.createdAt) && Objects.equals(updatedAt, target.updatedAt) && Objects.equals(isApprove, target.isApprove) && Objects.equals(parentId, target.parentId) && Objects.equals(measureUnitId, target.measureUnitId) && Objects.equals(isAdditional, target.isAdditional) && Objects.equals(type, target.type) && Objects.equals(basicDate, target.basicDate) && Objects.equals(planDate, target.planDate) && Objects.equals(nationalProjectGoal, target.nationalProjectGoal) && Objects.equals(nationalProjectResult, target.nationalProjectResult) && Objects.equals(nationalProjectCharact, target.nationalProjectCharact) && Objects.equals(resultDueDate, target.resultDueDate) && Objects.equals(resultAssigned, target.resultAssigned) && Objects.equals(metaId, target.metaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusId, name, typeId, unit, basicValue, planValue, comment, projectId, createdAt, updatedAt, isApprove, parentId, measureUnitId, isAdditional, type, basicDate, planDate, nationalProjectGoal, nationalProjectResult, nationalProjectCharact, resultDueDate, resultAssigned, metaId);
    }
}
