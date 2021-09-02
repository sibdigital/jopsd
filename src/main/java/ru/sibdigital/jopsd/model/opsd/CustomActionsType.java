package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Table(name = "custom_actions_types", indexes = {
        @Index(name = "index_custom_actions_types_on_type_id", columnList = "type_id"),
        @Index(name = "index_custom_actions_types_on_custom_action_id", columnList = "custom_action_id")
})
@Entity
public class CustomActionsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "type_id")
    private Type type;
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
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