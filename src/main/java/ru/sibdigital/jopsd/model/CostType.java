package ru.sibdigital.jopsd.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "cost_types")
@Entity
public class CostType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "unit", nullable = false)
    private String unit;

    @Lob
    @Column(name = "unit_plural", nullable = false)
    private String unitPlural;

    @Column(name = "\"default\"", nullable = false)
    private Boolean _default = false;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean get_default() {
        return _default;
    }

    public void set_default(Boolean _default) {
        this._default = _default;
    }

    public String getUnitPlural() {
        return unitPlural;
    }

    public void setUnitPlural(String unitPlural) {
        this.unitPlural = unitPlural;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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