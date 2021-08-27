package ru.sibdigital.jopsd.model;

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

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "custom_action_id")
    private Long customActionId;

    public Long getCustomActionId() {
        return customActionId;
    }

    public void setCustomActionId(Long customActionId) {
        this.customActionId = customActionId;
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