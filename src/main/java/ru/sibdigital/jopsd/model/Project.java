package ru.sibdigital.jopsd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private Long parentId;
    private Timestamp createdOn;
    private Timestamp updatedOn;
    private String identifier;
    private Long status;
    private Long lft;
    private Long rgt;
    private Long projectApproveStatusId;
    private Long projectStatusId;
    private Timestamp startDate;
    private Timestamp dueDate;
    private Long nationalProjectId;
    private Long federalProjectId;
    private String type;
    private Date factStartDate;
    private Date factDueDate;
    private Double investAmount;
    private Boolean isProgram;
    private Long addressId;
    private String nationalProjectTarget;
    private String governmentProgram;
    private String missionOfHead;

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
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
    @Column(name = "project_approve_status_id")
    public Long getProjectApproveStatusId() {
        return projectApproveStatusId;
    }

    public void setProjectApproveStatusId(Long projectApproveStatusId) {
        this.projectApproveStatusId = projectApproveStatusId;
    }

    @Basic
    @Column(name = "project_status_id")
    public Long getProjectStatusId() {
        return projectStatusId;
    }

    public void setProjectStatusId(Long projectStatusId) {
        this.projectStatusId = projectStatusId;
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
    @Column(name = "national_project_id")
    public Long getNationalProjectId() {
        return nationalProjectId;
    }

    public void setNationalProjectId(Long nationalProjectId) {
        this.nationalProjectId = nationalProjectId;
    }

    @Basic
    @Column(name = "federal_project_id")
    public Long getFederalProjectId() {
        return federalProjectId;
    }

    public void setFederalProjectId(Long federalProjectId) {
        this.federalProjectId = federalProjectId;
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
    public Date getFactStartDate() {
        return factStartDate;
    }

    public void setFactStartDate(Date factStartDate) {
        this.factStartDate = factStartDate;
    }

    @Basic
    @Column(name = "fact_due_date")
    public Date getFactDueDate() {
        return factDueDate;
    }

    public void setFactDueDate(Date factDueDate) {
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
    @Column(name = "address_id")
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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
}
