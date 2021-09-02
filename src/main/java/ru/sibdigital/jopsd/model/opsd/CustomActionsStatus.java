package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Table(name = "custom_actions_statuses", indexes = {
        @Index(name = "index_custom_actions_statuses_on_status_id", columnList = "status_id"),
        @Index(name = "index_custom_actions_statuses_on_custom_action_id", columnList = "custom_action_id")
})
@Entity
public class CustomActionsStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "status_id")
    private Status status;
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
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