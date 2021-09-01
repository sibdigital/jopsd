package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Table(name = "webhooks_projects", indexes = {
        @Index(name = "index_webhooks_projects_on_project_id", columnList = "project_id"),
        @Index(name = "index_webhooks_projects_on_webhooks_webhook_id", columnList = "webhooks_webhook_id")
})
@Entity
public class WebhooksProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "webhooks_webhook_id")
    private WebhooksWebhook webhooksWebhook;

    public WebhooksWebhook getWebhooksWebhook() {
        return webhooksWebhook;
    }

    public void setWebhooksWebhook(WebhooksWebhook webhooksWebhook) {
        this.webhooksWebhook = webhooksWebhook;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}