package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "custom_actions_projects", indexes = {
        @Index(name = "index_custom_actions_projects_on_project_id", columnList = "project_id"),
        @Index(name = "index_custom_actions_projects_on_custom_action_id", columnList = "custom_action_id")
})
@Entity
public class CustomActionsProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "custom_action_id")
    private Long customActionId;

    public Long getCustomActionId() {
        return customActionId;
    }

    public void setCustomActionId(Long customActionId) {
        this.customActionId = customActionId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}