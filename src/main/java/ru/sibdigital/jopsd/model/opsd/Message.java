package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "messages", indexes = {
        @Index(name = "index_messages_on_board_id_and_updated_on", columnList = "board_id, updated_on"),
        @Index(name = "index_messages_on_last_reply_id", columnList = "last_reply_id"),
        @Index(name = "messages_board_id", columnList = "board_id"),
        @Index(name = "messages_parent_id", columnList = "parent_id"),
        @Index(name = "index_messages_on_author_id", columnList = "author_id"),
        @Index(name = "index_messages_on_created_on", columnList = "created_on")
})
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "board_id")
    private Board board;
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "parent_id")
    private Message parent;
    public Message getParent() {
        return parent;
    }
    public void setParent(Message parent) {
        this.parent = parent;
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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "last_reply_id")
    private Message lastReply;
    public Message getLastReply() {
        return lastReply;
    }
    public void setLastReply(Message lastReply) {
        this.lastReply = lastReply;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "work_package_id")
    private WorkPackage workPackage;
    public WorkPackage getWorkPackage() {
        return workPackage;
    }
    public void setWorkPackage(WorkPackage workPackage) {
        this.workPackage = workPackage;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "document_id")
    private Document document;
    public Document getDocument() {
        return document;
    }
    public void setDocument(Document document) {
        this.document = document;
    }

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "replies_count", nullable = false)
    private Integer repliesCount;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "updated_on", nullable = false)
    private Timestamp updatedOn;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "sticky")
    private Integer sticky;

    @Column(name = "sticked_on")
    private Timestamp stickedOn;

    public Timestamp getStickedOn() {
        return stickedOn;
    }

    public void setStickedOn(Timestamp stickedOn) {
        this.stickedOn = stickedOn;
    }

    public Integer getSticky() {
        return sticky;
    }

    public void setSticky(Integer sticky) {
        this.sticky = sticky;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

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

    public Integer getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(Integer repliesCount) {
        this.repliesCount = repliesCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}