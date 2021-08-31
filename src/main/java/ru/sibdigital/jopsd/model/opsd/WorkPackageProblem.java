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
@Table(name = "work_package_problems", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WorkPackageProblem {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "WORK_PACKAGE_PROBLEMS_GEN", sequenceName = "work_package_problems_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORK_PACKAGE_PROBLEMS_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    private Long projectId;
    private Long workPackageId;
    private Long userCreatorId;
    private Long riskId;
    private Long userSourceId;
    private Long organizationSourceId;
    private String description;
    private String status;
    private String type;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private LocalDateTime solutionDate;

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
    @Column(name = "user_creator_id")
    public Long getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(Long userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    @Basic
    @Column(name = "risk_id")
    public Long getRiskId() {
        return riskId;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    @Basic
    @Column(name = "user_source_id")
    public Long getUserSourceId() {
        return userSourceId;
    }

    public void setUserSourceId(Long userSourceId) {
        this.userSourceId = userSourceId;
    }

    @Basic
    @Column(name = "organization_source_id")
    public Long getOrganizationSourceId() {
        return organizationSourceId;
    }

    public void setOrganizationSourceId(Long organizationSourceId) {
        this.organizationSourceId = organizationSourceId;
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
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "solution_date")
    public LocalDateTime getSolutionDate() {
        return solutionDate;
    }

    public void setSolutionDate(LocalDateTime solutionDate) {
        this.solutionDate = solutionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkPackageProblem that = (WorkPackageProblem) o;
        return Objects.equals(id, that.id) && Objects.equals(projectId, that.projectId) && Objects.equals(workPackageId, that.workPackageId) && Objects.equals(userCreatorId, that.userCreatorId) && Objects.equals(riskId, that.riskId) && Objects.equals(userSourceId, that.userSourceId) && Objects.equals(organizationSourceId, that.organizationSourceId) && Objects.equals(description, that.description) && Objects.equals(status, that.status) && Objects.equals(type, that.type) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(solutionDate, that.solutionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, workPackageId, userCreatorId, riskId, userSourceId, organizationSourceId, description, status, type, createdAt, updatedAt, solutionDate);
    }
}
