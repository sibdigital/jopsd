package ru.sibdigital.jopsd.model;

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

    @Lob
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

    @Column(name = "color_id")
    private Integer colorId;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "is_standard", nullable = false)
    private Boolean isStandard = false;

    @Lob
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

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
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