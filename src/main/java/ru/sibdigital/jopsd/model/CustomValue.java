package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "custom_values", indexes = {
        @Index(name = "custom_values_customized", columnList = "customized_type, customized_id"),
        @Index(name = "index_custom_values_on_custom_field_id", columnList = "custom_field_id")
})
@Entity
public class CustomValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "customized_type", nullable = false, length = 30)
    private String customizedType;

    @Column(name = "customized_id", nullable = false)
    private Long customizedId;

    @Column(name = "custom_field_id", nullable = false)
    private Long customFieldId;

    @Lob
    @Column(name = "value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(Long customFieldId) {
        this.customFieldId = customFieldId;
    }

    public Long getCustomizedId() {
        return customizedId;
    }

    public void setCustomizedId(Long customizedId) {
        this.customizedId = customizedId;
    }

    public String getCustomizedType() {
        return customizedType;
    }

    public void setCustomizedType(String customizedType) {
        this.customizedType = customizedType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}