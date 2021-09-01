package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
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
    @JoinColumn(name = "possibility_id")
    private Enumeration possibility;
    public Enumeration getPossibility() {
        return possibility;
    }
    public void setPossibility(Enumeration possibility) {
        this.possibility = possibility;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "importance_id")
    private Enumeration importance;
    public Enumeration getImportance() {
        return importance;
    }
    public void setImportance(Enumeration importance) {
        this.importance = importance;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "color_id")
    private Color color;
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "owner_id")
    private User owner;
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "project_section_id")
    private Enumeration projectSection;
    public Enumeration getProjectSection() {
        return projectSection;
    }
    public void setProjectSection(Enumeration projectSection) {
        this.projectSection = projectSection;
    }

    private String name;
    private String description;
    private String type;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isApprove;
    private Boolean isPossibility;
    private String solution;

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
    @Column(name = "is_approve")
    public Boolean getApprove() {
        return isApprove;
    }

    public void setApprove(Boolean approve) {
        isApprove = approve;
    }

    @Basic
    @Column(name = "is_possibility")
    public Boolean getIsPossibility() {
        return isPossibility;
    }
    public void setIsPossibility(Boolean possibility) {
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

}
