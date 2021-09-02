package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "wiki_pages", indexes = {
        @Index(name = "index_wiki_pages_on_parent_id", columnList = "parent_id"),
        @Index(name = "index_wiki_pages_on_wiki_id", columnList = "wiki_id"),
        @Index(name = "wiki_pages_wiki_id_slug", columnList = "wiki_id, slug", unique = true),
        @Index(name = "wiki_pages_wiki_id_title", columnList = "wiki_id, title")
})
@Entity
public class WikiPage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "wiki_id", nullable = false)
    private Wiki wiki;
    public Wiki getWiki() {
        return wiki;
    }
    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "parent_id")
    private WikiPage parent;
    public WikiPage getParent() {
        return parent;
    }
    public void setParent(WikiPage parent) {
        this.parent = parent;
    }

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "protected", nullable = false)
    private Boolean _protected = false;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean get_protected() {
        return _protected;
    }

    public void set_protected(Boolean _protected) {
        this._protected = _protected;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}