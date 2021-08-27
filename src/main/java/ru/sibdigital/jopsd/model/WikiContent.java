package ru.sibdigital.jopsd.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "wiki_contents", indexes = {
        @Index(name = "wiki_contents_page_id", columnList = "page_id"),
        @Index(name = "index_wiki_contents_on_author_id", columnList = "author_id"),
        @Index(name = "index_wiki_contents_on_page_id_and_updated_on", columnList = "page_id, updated_on")
})
@Entity
public class WikiContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "page_id", nullable = false)
    private Long pageId;

    @Column(name = "author_id")
    private Long authorId;

    @Lob
    @Column(name = "text")
    private String text;

    @Column(name = "updated_on", nullable = false)
    private Timestamp updatedOn;

    @Column(name = "lock_version", nullable = false)
    private Integer lockVersion;

    public Integer getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Integer lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}