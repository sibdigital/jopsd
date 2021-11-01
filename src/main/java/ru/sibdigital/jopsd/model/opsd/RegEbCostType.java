package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reg_eb_cost_types")
@Entity
public class RegEbCostType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_eb_cost_type", nullable = false)
    private EbCostType ebCostType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cost_type", nullable = false)
    private CostType costType;

    public CostType getCostType() {
        return costType;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    public EbCostType getEbCostType() {
        return ebCostType;
    }

    public void setEbCostType(EbCostType ebCostType) {
        this.ebCostType = ebCostType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}