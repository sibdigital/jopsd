package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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