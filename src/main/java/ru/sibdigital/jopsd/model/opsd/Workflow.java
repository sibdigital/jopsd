package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Table(name = "workflows", indexes = {
        @Index(name = "index_workflows_on_new_status_id", columnList = "new_status_id"),
        @Index(name = "wkfs_role_type_old_status", columnList = "role_id, type_id, old_status_id"),
        @Index(name = "index_workflows_on_old_status_id", columnList = "old_status_id"),
        @Index(name = "index_workflows_on_role_id", columnList = "role_id")
})
@Entity
public class Workflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "old_status_id", nullable = false)
    private Status oldStatus;
    public Status getOldStatus() {
        return oldStatus;
    }
    public void setOldStatus(Status oldStatus) {
        this.oldStatus = oldStatus;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "new_status_id", nullable = false)
    private Status newStatus;
    public Status getNewStatus() {
        return newStatus;
    }
    public void setNewStatus(Status newStatus) {
        this.newStatus = newStatus;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "assignee", nullable = false)
    private Boolean assignee = false;

    @Column(name = "author", nullable = false)
    private Boolean author = false;

    public Boolean getAuthor() {
        return author;
    }

    public void setAuthor(Boolean author) {
        this.author = author;
    }

    public Boolean getAssignee() {
        return assignee;
    }

    public void setAssignee(Boolean assignee) {
        this.assignee = assignee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}