package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "attachable_journals", indexes = {
        @Index(name = "index_attachable_journals_on_attachment_id", columnList = "attachment_id"),
        @Index(name = "index_attachable_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class AttachableJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

    @Column(name = "attachment_id", nullable = false)
    private Long attachmentId;

    @Lob
    @Column(name = "filename", nullable = false)
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
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