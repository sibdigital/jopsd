package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "user_tasks")
@Entity
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_creator_id")
    private User userCreator;
    public User getUserCreator() {
        return userCreator;
    }
    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;
    public User getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "related_task_id")
    private UserTask relatedTask;
    public UserTask getRelatedTask() {
        return relatedTask;
    }
    public void setRelatedTask(UserTask relatedTask) {
        this.relatedTask = relatedTask;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "period_id")
    private Enumeration period;
    public Enumeration getPeriod() {
        return period;
    }
    public void setPeriod(Enumeration period) {
        this.period = period;
    }

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "object_type")
    private String objectType;

    @Column(name = "kind")
    private String kind;

    @Column(name = "text")
    private String text;

    @Column(name = "due_date")
    private Timestamp dueDate;

    @Column(name = "completed")
    private Boolean completed;

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

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}