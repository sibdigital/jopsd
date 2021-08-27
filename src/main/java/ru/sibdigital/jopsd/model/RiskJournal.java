package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "risk_journals", indexes = {
        @Index(name = "index_risk_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class RiskJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

    @Column(name = "project_id")
    private Long projectId;

    @Lob
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "possibility_id")
    private Long possibilityId;

    @Column(name = "importance_id")
    private Long importanceId;

    @Lob
    @Column(name = "type")
    private String type;

    @Column(name = "color_id")
    private Long colorId;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "is_possibility")
    private Boolean isPossibility;

    @Column(name = "is_approve")
    private Boolean isApprove;

    @Lob
    @Column(name = "solution")
    private String solution;

    @Column(name = "project_section_id")
    private Integer projectSectionId;

    public Integer getProjectSectionId() {
        return projectSectionId;
    }

    public void setProjectSectionId(Integer projectSectionId) {
        this.projectSectionId = projectSectionId;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Boolean getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Boolean isApprove) {
        this.isApprove = isApprove;
    }

    public Boolean getIsPossibility() {
        return isPossibility;
    }

    public void setIsPossibility(Boolean isPossibility) {
        this.isPossibility = isPossibility;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getImportanceId() {
        return importanceId;
    }

    public void setImportanceId(Long importanceId) {
        this.importanceId = importanceId;
    }

    public Long getPossibilityId() {
        return possibilityId;
    }

    public void setPossibilityId(Long possibilityId) {
        this.possibilityId = possibilityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}