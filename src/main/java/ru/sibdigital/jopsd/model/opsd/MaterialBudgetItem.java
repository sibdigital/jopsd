package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "material_budget_items", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MaterialBudgetItem {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "MATERIAL_BUDGET_ITEMS_GEN", sequenceName = "material_budget_items_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATERIAL_BUDGET_ITEMS_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "cost_object_id")
    private CostObject costObject;
    public CostObject getCostObject() {
        return costObject;
    }
    public void setCostObject(CostObject costObject) {
        this.costObject = costObject;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "cost_type_id")
    private CostType costType;
    public CostType getCostType() {
        return costType;
    }
    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    private Double units;
    private String comments;
    private BigDecimal budget;
    private BigDecimal passportUnits;
    private BigDecimal consolidateUnits;
    private Integer planYear;

    @Basic
    @Column(name = "units")
    public Double getUnits() {
        return units;
    }

    public void setUnits(Double units) {
        this.units = units;
    }

    @Basic
    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "budget")
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Basic
    @Column(name = "passport_units")
    public BigDecimal getPassportUnits() {
        return passportUnits;
    }

    public void setPassportUnits(BigDecimal passportUnits) {
        this.passportUnits = passportUnits;
    }

    @Basic
    @Column(name = "consolidate_units")
    public BigDecimal getConsolidateUnits() {
        return consolidateUnits;
    }

    public void setConsolidateUnits(BigDecimal consolidateUnits) {
        this.consolidateUnits = consolidateUnits;
    }

    @Basic
    @Column(name = "plan_year")
    public Integer getPlanYear() {
        return planYear;
    }

    public void setPlanYear(Integer planYear) {
        this.planYear = planYear;
    }

}
