package ru.sibdigital.jopsd.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "head_performance_indicator_values")
@Entity
public class HeadPerformanceIndicatorValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "head_performance_indicator_id")
    private Long headPerformanceIndicatorId;

    @Lob
    @Column(name = "type")
    private String type;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "quarter")
    private Integer quarter;

    @Column(name = "month")
    private Integer month;

    @Column(name = "value", precision = 131089)
    private BigDecimal value;

    @Column(name = "sort_code")
    private Integer sortCode;

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getHeadPerformanceIndicatorId() {
        return headPerformanceIndicatorId;
    }

    public void setHeadPerformanceIndicatorId(Long headPerformanceIndicatorId) {
        this.headPerformanceIndicatorId = headPerformanceIndicatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}