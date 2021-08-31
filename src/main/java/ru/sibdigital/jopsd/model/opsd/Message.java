package ru.sibdigital.jopsd.model.opsd;

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

    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "replies_count", nullable = false)
    private Integer repliesCount;

    @Column(name = "last_reply_id")
    private Long lastReplyId;

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

    @Column(name = "work_package_id")
    private Long workPackageId;

    @Column(name = "document_id")
    private Long documentId;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getWorkPackageId() {
        return workPackageId;
    }

    public void setWorkPackageId(Long workPackageId) {
        this.workPackageId = workPackageId;
    }

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

    public Long getLastReplyId() {
        return lastReplyId;
    }

    public void setLastReplyId(Long lastReplyId) {
        this.lastReplyId = lastReplyId;
    }

    public Integer getRepliesCount() {
        return repliesCount;
    }

    public void setRepliesCount(Integer repliesCount) {
        this.repliesCount = repliesCount;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}