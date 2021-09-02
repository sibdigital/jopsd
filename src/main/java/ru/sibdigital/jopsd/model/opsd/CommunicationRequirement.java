package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "communication_requirements")
@Entity
public class CommunicationRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

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
    @JoinColumn(name = "stakeholder_id")
    private Stakeholder stakeholder;
    public Stakeholder getStakeholder() {
        return stakeholder;
    }
    public void setStakeholder(Stakeholder stakeholder) {
        this.stakeholder = stakeholder;
    }

    @Column(name = "period")
    private String period;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "kind_info")
    private String kindInfo;

    @Column(name = "stakeholder_type")
    private String stakeholderType;

    public String getStakeholderType() {
        return stakeholderType;
    }

    public void setStakeholderType(String stakeholderType) {
        this.stakeholderType = stakeholderType;
    }

    public String getKindInfo() {
        return kindInfo;
    }

    public void setKindInfo(String kindInfo) {
        this.kindInfo = kindInfo;
    }

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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}