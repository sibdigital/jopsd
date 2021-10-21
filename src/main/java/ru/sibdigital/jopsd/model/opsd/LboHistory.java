package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "lbo_history")
@Entity
public class LboHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "primary_id", nullable = false)
    private Long primaryId;

    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "year")
    private Integer year;

    @Column(name = "sum", precision = 15, scale = 4)
    private BigDecimal sum;

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Long primaryId) {
        this.primaryId = primaryId;
    }
}