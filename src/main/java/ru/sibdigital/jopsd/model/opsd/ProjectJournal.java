package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "journal_id")
    private Journal journal;
    public Journal getJournal() {
        return journal;
    }
    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "responsible_id")
    private User responsible;
    public User getResponsible() {
        return responsible;
    }
    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "work_packages_responsible_id")
    private User workPackagesResponsible;
    public User getWorkPackagesResponsible() {
        return workPackagesResponsible;
    }
    public void setWorkPackagesResponsible(User workPackagesResponsible) {
        this.workPackagesResponsible = workPackagesResponsible;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "project_approve_status_id")
    private Enumeration projectApproveStatus;
    public Enumeration getProjectApproveStatus() {
        return projectApproveStatus;
    }
    public void setProjectApproveStatus(Enumeration projectApproveStatus) {
        this.projectApproveStatus = projectApproveStatus;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "project_status_id")
    private Enumeration projectStatus;
    public Enumeration getProjectStatus() {
        return projectStatus;
    }
    public void setProjectStatus(Enumeration projectStatus) {
        this.projectStatus = projectStatus;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "parent_id")
    private Project parent;
    public Project getParent() {
        return parent;
    }
    public void setParent(Project parent) {
        this.parent = parent;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "national_project_id")
    private NationalProject nationalProject;
    public NationalProject getNationalProject() {
        return nationalProject;
    }
    public void setNationalProject(NationalProject nationalProject) {
        this.nationalProject = nationalProject;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "federal_project_id")
    private NationalProject federalProject;
    public NationalProject getFederalProject() {
        return federalProject;
    }
    public void setFederalProject(NationalProject federalProject) {
        this.federalProject = federalProject;
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = false;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "identifier")
    private String identifier;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "lft")
    private Integer lft;

    @Column(name = "rgt")
    private Integer rgt;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "due_date")
    private Timestamp dueDate;

    @Column(name = "national_project_target")
    private String nationalProjectTarget;

    @Column(name = "government_program")
    private String governmentProgram;

    @Column(name = "mission_of_head")
    private String missionOfHead;

    @Column(name = "project_type_id")
    private Long projectTypeId;

    public Long getProjectTypeId() {
        return projectTypeId;
    }

    public void setProjectTypeId(Long projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}