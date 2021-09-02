package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "role_id")
    private Role role;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "custom_action_id")
    private CustomAction customAction;
    public CustomAction getCustomAction() {
        return customAction;
    }
    public void setCustomAction(CustomAction customAction) {
        this.customAction = customAction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}