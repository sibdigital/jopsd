package ru.sibdigital.jopsd.model.opsd;

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

    @Column(name = "status_id")
    private Long statusId;

    @Column(name = "custom_action_id")
    private Long customActionId;

    public Long getCustomActionId() {
        return customActionId;
    }

    public void setCustomActionId(Long customActionId) {
        this.customActionId = customActionId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}