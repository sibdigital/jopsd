package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "menu_items", indexes = {
        @Index(name = "index_menu_items_on_navigatable_id_and_title", columnList = "navigatable_id, title"),
        @Index(name = "index_menu_items_on_parent_id", columnList = "parent_id")
})
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "options")
    private String options;

    @Column(name = "navigatable_id")
    private Long navigatableId;

    @Column(name = "type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNavigatableId() {
        return navigatableId;
    }

    public void setNavigatableId(Long navigatableId) {
        this.navigatableId = navigatableId;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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