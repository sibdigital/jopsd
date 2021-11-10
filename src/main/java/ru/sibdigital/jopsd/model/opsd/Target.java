package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "targets", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Target {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "TARGETS_GEN", sequenceName = "targets_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TARGETS_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "status_id")
    private Enumeration status;
    public Enumeration getStatus() {
        return status;
    }
    public void setStatus(Enumeration status) {
        this.status = status;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "type_id")
    private Enumeration targetType;
    public Enumeration getTargetType() {
        return targetType;
    }
    public void setTargetType(Enumeration targetType) {
        this.targetType = targetType;
    }

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
    @JoinColumn(name = "measure_unit_id")
    private MeasureUnit measureUnit;
    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }
    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    // По default из ruby ставится 0, что приводит к ошибкам
//    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinColumn(name = "parent_id")
//    private Target parent;
//    public Target getParent() {
//        return parent;
//    }
//    public void setParent(Target parent) {
//        this.parent = parent;
//    }

    private Long parentId;

    private String name;
    private String unit;
    private Double basicValue;
    private Double planValue;
    private String comment;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isApprove;
    private Boolean isAdditional;
    private String type;
    private LocalDateTime basicDate;
    private LocalDateTime planDate;
    private String nationalProjectGoal;
    private String nationalProjectResult;
    private String nationalProjectCharact;
    private LocalDateTime resultDueDate;
    private Long resultAssigned;
    private Long metaId;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "basic_value")
    public Double getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(Double basicValue) {
        this.basicValue = basicValue;
    }

    @Basic
    @Column(name = "plan_value")
    public Double getPlanValue() {
        return planValue;
    }

    public void setPlanValue(Double planValue) {
        this.planValue = planValue;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    @Column(name = "is_additional")
    public Boolean getAdditional() {
        return isAdditional;
    }

    public void setAdditional(Boolean additional) {
        isAdditional = additional;
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
    @Column(name = "basic_date")
    public LocalDateTime getBasicDate() {
        return basicDate;
    }

    public void setBasicDate(LocalDateTime basicDate) {
        this.basicDate = basicDate;
    }

    @Basic
    @Column(name = "plan_date")
    public LocalDateTime getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDateTime planDate) {
        this.planDate = planDate;
    }

    @Basic
    @Column(name = "national_project_goal")
    public String getNationalProjectGoal() {
        return nationalProjectGoal;
    }

    public void setNationalProjectGoal(String nationalProjectGoal) {
        this.nationalProjectGoal = nationalProjectGoal;
    }

    @Basic
    @Column(name = "national_project_result")
    public String getNationalProjectResult() {
        return nationalProjectResult;
    }

    public void setNationalProjectResult(String nationalProjectResult) {
        this.nationalProjectResult = nationalProjectResult;
    }

    @Basic
    @Column(name = "national_project_charact")
    public String getNationalProjectCharact() {
        return nationalProjectCharact;
    }

    public void setNationalProjectCharact(String nationalProjectCharact) {
        this.nationalProjectCharact = nationalProjectCharact;
    }

    @Basic
    @Column(name = "result_due_date")
    public LocalDateTime getResultDueDate() {
        return resultDueDate;
    }

    public void setResultDueDate(LocalDateTime resultDueDate) {
        this.resultDueDate = resultDueDate;
    }

    @Basic
    @Column(name = "result_assigned")
    public Long getResultAssigned() {
        return resultAssigned;
    }

    public void setResultAssigned(Long resultAssigned) {
        this.resultAssigned = resultAssigned;
    }

    @Basic
    @Column(name = "meta_id")
    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }

    @Basic
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
