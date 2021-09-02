package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "project_id")
    private Project project;
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}