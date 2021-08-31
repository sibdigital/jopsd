package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "custom_actions_roles", indexes = {
        @Index(name = "index_custom_actions_roles_on_custom_action_id", columnList = "custom_action_id"),
        @Index(name = "index_custom_actions_roles_on_role_id", columnList = "role_id")
})
@Entity
public class CustomActionsRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "custom_action_id")
    private Long customActionId;

    public Long getCustomActionId() {
        return customActionId;
    }

    public void setCustomActionId(Long customActionId) {
        this.customActionId = customActionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}