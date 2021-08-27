package ru.sibdigital.jopsd.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Table(name = "members", indexes = {
        @Index(name = "index_members_on_user_id_and_project_id", columnList = "user_id, project_id", unique = true),
        @Index(name = "index_members_on_project_id", columnList = "project_id"),
        @Index(name = "index_members_on_user_id", columnList = "user_id")
})
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "mail_notification", nullable = false)
    private Boolean mailNotification = false;

    @Column(name = "busyness", precision = 131089)
    private BigDecimal busyness;

    public BigDecimal getBusyness() {
        return busyness;
    }

    public void setBusyness(BigDecimal busyness) {
        this.busyness = busyness;
    }

    public Boolean getMailNotification() {
        return mailNotification;
    }

    public void setMailNotification(Boolean mailNotification) {
        this.mailNotification = mailNotification;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}