package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "work_package_targets", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WorkPackageTarget {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "WORK_PACKAGE_TARGETS_GEN", sequenceName = "work_package_targets_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORK_PACKAGE_TARGETS_GEN")
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
    @JoinColumn(name = "target_id")
    private Target target;
    public Target getTarget() {
        return target;
    }
    public void setTarget(Target target) {
        this.target = target;
    }

    private Integer year;
    private Integer quarter;
    private Integer month;
    private BigDecimal value;
    private String type;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private BigDecimal planValue;

    @Basic
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "quarter")
    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    @Basic
    @Column(name = "month")
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Basic
    @Column(name = "value")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
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
    @Column(name = "plan_value")
    public BigDecimal getPlanValue() {
        return planValue;
    }

    public void setPlanValue(BigDecimal planValue) {
        this.planValue = planValue;
    }
}
