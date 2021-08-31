package ru.sibdigital.jopsd.model.opsd;

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

    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @Column(name = "old_status_id", nullable = false)
    private Long oldStatusId;

    @Column(name = "new_status_id", nullable = false)
    private Long newStatusId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getNewStatusId() {
        return newStatusId;
    }

    public void setNewStatusId(Long newStatusId) {
        this.newStatusId = newStatusId;
    }

    public Long getOldStatusId() {
        return oldStatusId;
    }

    public void setOldStatusId(Long oldStatusId) {
        this.oldStatusId = oldStatusId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}