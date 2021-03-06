package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "types", indexes = {
        @Index(name = "index_types_on_color_id", columnList = "color_id")
})
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "\"position\"")
    private Integer position;

    @Column(name = "is_in_roadmap", nullable = false)
    private Boolean isInRoadmap = false;

    @Column(name = "is_milestone", nullable = false)
    private Boolean isMilestone = false;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "is_standard", nullable = false)
    private Boolean isStandard = false;

    @Column(name = "attribute_groups")
    private String attributeGroups;

    public String getAttributeGroups() {
        return attributeGroups;
    }

    public void setAttributeGroups(String attributeGroups) {
        this.attributeGroups = attributeGroups;
    }

    public Boolean getIsStandard() {
        return isStandard;
    }

    public void setIsStandard(Boolean isStandard) {
        this.isStandard = isStandard;
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getIsMilestone() {
        return isMilestone;
    }

    public void setIsMilestone(Boolean isMilestone) {
        this.isMilestone = isMilestone;
    }

    public Boolean getIsInRoadmap() {
        return isInRoadmap;
    }

    public void setIsInRoadmap(Boolean isInRoadmap) {
        this.isInRoadmap = isInRoadmap;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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