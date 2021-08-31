package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "projects", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Project {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "PROJECTS_GEN", sequenceName = "projects_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJECTS_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    private String name;
    private String description;
    private Boolean isPublic;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private String identifier;
    private Long status;
    private Long lft;
    private Long rgt;
    private Timestamp startDate;
    private Timestamp dueDate;
    private String type;
    private LocalDateTime factStartDate;
    private LocalDateTime factDueDate;
    private Double investAmount;
    private Boolean isProgram;
    private String nationalProjectTarget;
    private String governmentProgram;
    private String missionOfHead;
    private Long metaId;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Project parent;
    public Project getParent() {
        return parent;
    }
    public void setParent(Project parent) {
        this.parent = parent;
    }

    @ManyToOne
    @JoinColumn(name = "project_approve_status_id")
    private Enumeration projectApproveStatus;
    public Enumeration getProjectApproveStatus() {
        return projectApproveStatus;
    }
    public void setProjectApproveStatus(Enumeration projectApproveStatus) {
        this.projectApproveStatus = projectApproveStatus;
    }

    @ManyToOne
    @JoinColumn(name = "project_status_id")
    private Enumeration projectStatus;
    public Enumeration getProjectStatus() {
        return projectStatus;
    }
    public void setProjectStatus(Enumeration projectStatus) {
        this.projectStatus = projectStatus;
    }

    @ManyToOne
    @JoinColumn(name = "national_project_id")
    private NationalProject nationalProject;
    public NationalProject getNationalProject() {
        return nationalProject;
    }
    public void setNationalProject(NationalProject nationalProject) {
        this.nationalProject = nationalProject;
    }

    @ManyToOne
    @JoinColumn(name = "federal_project_id")
    private NationalProject federalProject;
    public NationalProject getFederalProject() {
        return federalProject;
    }
    public void setFederalProject(NationalProject federalProject) {
        this.federalProject = federalProject;
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
    @Column(name = "is_public")
    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    @Basic
    @Column(name = "created_on")
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Basic
    @Column(name = "updated_on")
    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Basic
    @Column(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Basic
    @Column(name = "status")
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Basic
    @Column(name = "lft")
    public Long getLft() {
        return lft;
    }

    public void setLft(Long lft) {
        this.lft = lft;
    }

    @Basic
    @Column(name = "rgt")
    public Long getRgt() {
        return rgt;
    }

    public void setRgt(Long rgt) {
        this.rgt = rgt;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "due_date")
    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
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
    @Column(name = "fact_start_date")
    public LocalDateTime getFactStartDate() {
        return factStartDate;
    }

    public void setFactStartDate(LocalDateTime factStartDate) {
        this.factStartDate = factStartDate;
    }

    @Basic
    @Column(name = "fact_due_date")
    public LocalDateTime getFactDueDate() {
        return factDueDate;
    }

    public void setFactDueDate(LocalDateTime factDueDate) {
        this.factDueDate = factDueDate;
    }

    @Basic
    @Column(name = "invest_amount")
    public Double getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(Double investAmount) {
        this.investAmount = investAmount;
    }

    @Basic
    @Column(name = "is_program")
    public Boolean getProgram() {
        return isProgram;
    }

    public void setProgram(Boolean program) {
        isProgram = program;
    }

    @Basic
    @Column(name = "national_project_target")
    public String getNationalProjectTarget() {
        return nationalProjectTarget;
    }

    public void setNationalProjectTarget(String nationalProjectTarget) {
        this.nationalProjectTarget = nationalProjectTarget;
    }

    @Basic
    @Column(name = "government_program")
    public String getGovernmentProgram() {
        return governmentProgram;
    }

    public void setGovernmentProgram(String governmentProgram) {
        this.governmentProgram = governmentProgram;
    }

    @Basic
    @Column(name = "mission_of_head")
    public String getMissionOfHead() {
        return missionOfHead;
    }

    public void setMissionOfHead(String missionOfHead) {
        this.missionOfHead = missionOfHead;
    }

    @Basic
    @Column(name = "meta_id")
    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }

}
