package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "reg_eb_cost_types")
@Entity
public class RegEbCostType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_el_cost_type", nullable = false)
    private EbCostType idElCostType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cost_type", nullable = false)
    private CostType idCostType;

    public CostType getIdCostType() {
        return idCostType;
    }

    public void setIdCostType(CostType idCostType) {
        this.idCostType = idCostType;
    }

    public EbCostType getIdElCostType() {
        return idElCostType;
    }

    public void setIdElCostType(EbCostType idElCostType) {
        this.idElCostType = idElCostType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}