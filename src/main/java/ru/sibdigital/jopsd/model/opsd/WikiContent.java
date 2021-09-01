package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "page_id", nullable = false)
    private WikiPage page;
    public WikiPage getPage() {
        return page;
    }
    public void setPage(WikiPage page) {
        this.page = page;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "author_id")
    private User author;
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}