package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "work_package_problems", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WorkPackageProblem {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "WORK_PACKAGE_PROBLEMS_GEN", sequenceName = "work_package_problems_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORK_PACKAGE_PROBLEMS_GEN")
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
    @JoinColumn(name = "work_package_id")
    private WorkPackage workPackage;
    public WorkPackage getWorkPackage() {
        return workPackage;
    }
    public void setWorkPackage(WorkPackage workPackage) {
        this.workPackage = workPackage;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_creator_id")
    private User userCreator;
    public User getUserCreator() {
        return userCreator;
    }
    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "risk_id")
    private Risk risk;
    public Risk getRisk() {
        return risk;
    }
    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_source_id")
    private User userSource;
    public User getUserSource() {
        return userSource;
    }
    public void setUserSource(User userSource) {
        this.userSource = userSource;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "organization_source_id")
    private Organization organizationSource;
    public Organization getOrganizationSource() {
        return organizationSource;
    }
    public void setOrganizationSource(Organization organizationSource) {
        this.organizationSource = organizationSource;
    }

    private String description;
    private String status;
    private String type;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private LocalDateTime solutionDate;

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "solution_date")
    public LocalDateTime getSolutionDate() {
        return solutionDate;
    }

    public void setSolutionDate(LocalDateTime solutionDate) {
        this.solutionDate = solutionDate;
    }
}
