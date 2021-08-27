package ru.sibdigital.jopsd.model;

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

    @Column(name = "wiki_id", nullable = false)
    private Long wikiId;

    @Lob
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "protected", nullable = false)
    private Boolean _protected = false;

    @Column(name = "parent_id")
    private Long parentId;

    @Lob
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Long getWikiId() {
        return wikiId;
    }

    public void setWikiId(Long wikiId) {
        this.wikiId = wikiId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}