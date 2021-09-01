package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "journal_id")
    private Journal journal;
    public Journal getJournal() {
        return journal;
    }
    public void setJournal(Journal journal) {
        this.journal = journal;
    }

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
    @JoinColumn(name = "document_id")
    private Document document;
    public Document getDocument() {
        return document;
    }
    public void setDocument(Document document) {
        this.document = document;
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

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "replies_count", nullable = false)
    private Integer repliesCount;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "sticky")
    private Integer sticky;

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