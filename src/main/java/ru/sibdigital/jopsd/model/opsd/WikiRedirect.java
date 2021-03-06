package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "wiki_redirects", indexes = {
        @Index(name = "wiki_redirects_wiki_id_title", columnList = "wiki_id, title"),
        @Index(name = "index_wiki_redirects_on_wiki_id", columnList = "wiki_id")
})
@Entity
public class WikiRedirect {
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

    @Column(name = "title")
    private String title;

    @Column(name = "redirects_to")
    private String redirectsTo;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getRedirectsTo() {
        return redirectsTo;
    }

    public void setRedirectsTo(String redirectsTo) {
        this.redirectsTo = redirectsTo;
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