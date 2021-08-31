package ru.sibdigital.jopsd.model.opsd;

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

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;
    public Journal getJournal() {
        return journal;
    }
    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    @ManyToOne
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;
    public Attachment getAttachment() {
        return attachment;
    }
    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    @Column(name = "filename", nullable = false)
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}