package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "journals", indexes = {
        @Index(name = "index_journals_on_project_id", columnList = "project_id"),
        @Index(name = "index_journals_on_journable_type_and_journable_id_and_version", columnList = "journable_type, journable_id, version", unique = true),
        @Index(name = "index_journals_on_journable_type", columnList = "journable_type"),
        @Index(name = "index_journals_on_user_id", columnList = "user_id"),
        @Index(name = "index_journals_on_activity_type", columnList = "activity_type"),
        @Index(name = "index_journals_on_journable_id", columnList = "journable_id"),
        @Index(name = "index_journals_on_created_at", columnList = "created_at")
})
@Entity
public class Journal {
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

    @Column(name = "journable_type")
    private String journableType;

    @Column(name = "journable_id")
    private Long journableId;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "activity_type")
    private String activityType;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "next")
    private Integer next;

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getJournableId() {
        return journableId;
    }

    public void setJournableId(Long journableId) {
        this.journableId = journableId;
    }

    public String getJournableType() {
        return journableType;
    }

    public void setJournableType(String journableType) {
        this.journableType = journableType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}