package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "comments", indexes = {
        @Index(name = "index_comments_on_author_id", columnList = "author_id"),
        @Index(name = "index_comments_on_commented_id_and_commented_type", columnList = "commented_id, commented_type")
})
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    @Column(name = "commented_type", nullable = false, length = 30)
    private String commentedType;

    @Column(name = "commented_id", nullable = false)
    private Long commentedId;

    @Column(name = "comments")
    private String comments;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "updated_on", nullable = false)
    private Timestamp updatedOn;

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getCommentedId() {
        return commentedId;
    }

    public void setCommentedId(Long commentedId) {
        this.commentedId = commentedId;
    }

    public String getCommentedType() {
        return commentedType;
    }

    public void setCommentedType(String commentedType) {
        this.commentedType = commentedType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}