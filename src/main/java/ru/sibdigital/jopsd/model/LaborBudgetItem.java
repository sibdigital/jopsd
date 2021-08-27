package ru.sibdigital.jopsd.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "labor_budget_items")
@Entity
public class LaborBudgetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "cost_object_id", nullable = false)
    private Long costObjectId;

    @Column(name = "hours", nullable = false)
    private Double hours;

    @Column(name = "user_id")
    private Long userId;

    @Lob
    @Column(name = "comments", nullable = false)
    private String comments;

    @Column(name = "budget", precision = 15, scale = 4)
    private BigDecimal budget;

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Long getCostObjectId() {
        return costObjectId;
    }

    public void setCostObjectId(Long costObjectId) {
        this.costObjectId = costObjectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}