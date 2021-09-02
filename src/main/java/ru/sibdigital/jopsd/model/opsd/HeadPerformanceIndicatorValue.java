package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "head_performance_indicator_values")
@Entity
public class HeadPerformanceIndicatorValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "head_performance_indicator_id")
    private HeadPerformanceIndicator headPerformanceIndicator;
    public HeadPerformanceIndicator getHeadPerformanceIndicator() {
        return headPerformanceIndicator;
    }
    public void setHeadPerformanceIndicator(HeadPerformanceIndicator headPerformanceIndicator) {
        this.headPerformanceIndicator = headPerformanceIndicator;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}