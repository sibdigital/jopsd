package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Table(name = "webhooks_events", indexes = {
        @Index(name = "index_webhooks_events_on_webhooks_webhook_id", columnList = "webhooks_webhook_id")
})
@Entity
public class WebhooksEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}