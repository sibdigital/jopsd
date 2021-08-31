package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "work_package_targets", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WorkPackageTarget {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "WORK_PACKAGE_TARGETS_GEN", sequenceName = "work_package_targets_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORK_PACKAGE_TARGETS_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    private Long projectId;
    private Long workPackageId;
    private Long targetId;
    private Integer year;
    private Integer quarter;
    private Integer month;
    private BigDecimal value;
    private String type;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private BigDecimal planValue;

    @Basic
    @Column(name = "project_id")
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
    @Column(name = "target_id")
    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "quarter")
    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    @Basic
    @Column(name = "month")
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Basic
    @Column(name = "value")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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
    @Column(name = "plan_value")
    public BigDecimal getPlanValue() {
        return planValue;
    }

    public void setPlanValue(BigDecimal planValue) {
        this.planValue = planValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkPackageTarget that = (WorkPackageTarget) o;
        return Objects.equals(id, that.id) && Objects.equals(projectId, that.projectId) && Objects.equals(workPackageId, that.workPackageId) && Objects.equals(targetId, that.targetId) && Objects.equals(year, that.year) && Objects.equals(quarter, that.quarter) && Objects.equals(month, that.month) && Objects.equals(value, that.value) && Objects.equals(type, that.type) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(planValue, that.planValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, workPackageId, targetId, year, quarter, month, value, type, createdAt, updatedAt, planValue);
    }
}
