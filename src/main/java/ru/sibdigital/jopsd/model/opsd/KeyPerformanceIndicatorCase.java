package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Table(name = "key_performance_indicator_cases")
@Entity
public class KeyPerformanceIndicatorCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "key_performance_indicator_id")
    private KeyPerformanceIndicator keyPerformanceIndicator;
    public KeyPerformanceIndicator getKeyPerformanceIndicator() {
        return keyPerformanceIndicator;
    }
    public void setKeyPerformanceIndicator(KeyPerformanceIndicator keyPerformanceIndicator) {
        this.keyPerformanceIndicator = keyPerformanceIndicator;
    }

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "percent", precision = 131089)
    private BigDecimal percent;

    @Column(name = "min_value", precision = 131089)
    private BigDecimal minValue;

    @Column(name = "max_value", precision = 131089)
    private BigDecimal maxValue;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "period")
    private String period;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}