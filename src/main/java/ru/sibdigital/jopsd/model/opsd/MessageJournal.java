package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "message_journals", indexes = {
        @Index(name = "index_message_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class MessageJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

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

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "sticky")
    private Integer sticky;

    @Column(name = "document_id")
    private Integer documentId;

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
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

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}