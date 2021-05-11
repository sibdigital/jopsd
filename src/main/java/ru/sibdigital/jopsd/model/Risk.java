package ru.sibdigital.jopsd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "risks", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Risk {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "RISKS_GEN", sequenceName = "risks_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RISKS_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    private Long projectId;
    private String name;
    private String description;
    private Long possibilityId;
    private Long importanceId;
    private String type;
    private Long colorId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isApprove;
    private Long ownerId;
    private Boolean isPossibility;
    private String solution;
    private Long projectSectionId;

    @Basic
    @Column(name = "project_id")
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "possibility_id")
    public Long getPossibilityId() {
        return possibilityId;
    }

    public void setPossibilityId(Long possibilityId) {
        this.possibilityId = possibilityId;
    }

    @Basic
    @Column(name = "importance_id")
    public Long getImportanceId() {
        return importanceId;
    }

    public void setImportanceId(Long importanceId) {
        this.importanceId = importanceId;
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
    @Column(name = "color_id")
    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
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
    @Column(name = "owner_id")
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "is_possibility")
    public Boolean getPossibility() {
        return isPossibility;
    }

    public void setPossibility(Boolean possibility) {
        isPossibility = possibility;
    }

    @Basic
    @Column(name = "solution")
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Basic
    @Column(name = "project_section_id")
    public Long getProjectSectionId() {
        return projectSectionId;
    }

    public void setProjectSectionId(Long projectSectionId) {
        this.projectSectionId = projectSectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Risk risk = (Risk) o;
        return Objects.equals(id, risk.id) && Objects.equals(projectId, risk.projectId) && Objects.equals(name, risk.name) && Objects.equals(description, risk.description) && Objects.equals(possibilityId, risk.possibilityId) && Objects.equals(importanceId, risk.importanceId) && Objects.equals(type, risk.type) && Objects.equals(colorId, risk.colorId) && Objects.equals(createdAt, risk.createdAt) && Objects.equals(updatedAt, risk.updatedAt) && Objects.equals(isApprove, risk.isApprove) && Objects.equals(ownerId, risk.ownerId) && Objects.equals(isPossibility, risk.isPossibility) && Objects.equals(solution, risk.solution) && Objects.equals(projectSectionId, risk.projectSectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, name, description, possibilityId, importanceId, type, colorId, createdAt, updatedAt, isApprove, ownerId, isPossibility, solution, projectSectionId);
    }
}
