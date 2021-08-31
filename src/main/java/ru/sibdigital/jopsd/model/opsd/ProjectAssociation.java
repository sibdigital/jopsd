package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "project_associations", indexes = {
        @Index(name = "index_project_associations_on_project_a_id", columnList = "project_a_id"),
        @Index(name = "index_project_associations_on_project_b_id", columnList = "project_b_id")
})
@Entity
public class ProjectAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "project_a_id")
    private Long projectAId;

    @Column(name = "project_b_id")
    private Integer projectBId;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProjectBId() {
        return projectBId;
    }

    public void setProjectBId(Integer projectBId) {
        this.projectBId = projectBId;
    }

    public Long getProjectAId() {
        return projectAId;
    }

    public void setProjectAId(Long projectAId) {
        this.projectAId = projectAId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}