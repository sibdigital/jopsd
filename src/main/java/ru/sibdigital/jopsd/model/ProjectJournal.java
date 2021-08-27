package ru.sibdigital.jopsd.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "project_journals", indexes = {
        @Index(name = "index_project_journals_on_journal_id", columnList = "journal_id"),
        @Index(name = "index_project_journals_on_responsible_id", columnList = "responsible_id"),
        @Index(name = "index_project_journals_on_work_packages_responsible_id", columnList = "work_packages_responsible_id"),
        @Index(name = "index_project_journals_on_project_type_id", columnList = "project_type_id")
})
@Entity
public class ProjectJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = false;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Lob
    @Column(name = "identifier")
    private String identifier;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "lft")
    private Integer lft;

    @Column(name = "rgt")
    private Integer rgt;

    @Column(name = "project_type_id")
    private Long projectTypeId;

    @Column(name = "responsible_id")
    private Long responsibleId;

    @Column(name = "work_packages_responsible_id")
    private Long workPackagesResponsibleId;

    @Column(name = "project_approve_status_id")
    private Long projectApproveStatusId;

    @Column(name = "project_status_id")
    private Long projectStatusId;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "due_date")
    private Timestamp dueDate;

    @Column(name = "national_project_id")
    private Long nationalProjectId;

    @Column(name = "federal_project_id")
    private Long federalProjectId;

    @Lob
    @Column(name = "national_project_target")
    private String nationalProjectTarget;

    @Lob
    @Column(name = "government_program")
    private String governmentProgram;

    @Lob
    @Column(name = "mission_of_head")
    private String missionOfHead;

    public String getMissionOfHead() {
        return missionOfHead;
    }

    public void setMissionOfHead(String missionOfHead) {
        this.missionOfHead = missionOfHead;
    }

    public String getGovernmentProgram() {
        return governmentProgram;
    }

    public void setGovernmentProgram(String governmentProgram) {
        this.governmentProgram = governmentProgram;
    }

    public String getNationalProjectTarget() {
        return nationalProjectTarget;
    }

    public void setNationalProjectTarget(String nationalProjectTarget) {
        this.nationalProjectTarget = nationalProjectTarget;
    }

    public Long getFederalProjectId() {
        return federalProjectId;
    }

    public void setFederalProjectId(Long federalProjectId) {
        this.federalProjectId = federalProjectId;
    }

    public Long getNationalProjectId() {
        return nationalProjectId;
    }

    public void setNationalProjectId(Long nationalProjectId) {
        this.nationalProjectId = nationalProjectId;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Long getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(Long projectStatusId) {
        this.projectStatusId = projectStatusId;
    }

    public Long getProjectApproveStatusId() {
        return projectApproveStatusId;
    }

    public void setProjectApproveStatusId(Long projectApproveStatusId) {
        this.projectApproveStatusId = projectApproveStatusId;
    }

    public Long getWorkPackagesResponsibleId() {
        return workPackagesResponsibleId;
    }

    public void setWorkPackagesResponsibleId(Long workPackagesResponsibleId) {
        this.workPackagesResponsibleId = workPackagesResponsibleId;
    }

    public Long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Long responsibleId) {
        this.responsibleId = responsibleId;
    }

    public Long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public Integer getRgt() {
        return rgt;
    }

    public void setRgt(Integer rgt) {
        this.rgt = rgt;
    }

    public Integer getLft() {
        return lft;
    }

    public void setLft(Integer lft) {
        this.lft = lft;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
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