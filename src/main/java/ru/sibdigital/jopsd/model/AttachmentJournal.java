package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "attachment_journals", indexes = {
        @Index(name = "index_attachment_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class AttachmentJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

    @Column(name = "container_id")
    private Long containerId;

    @Column(name = "container_type", length = 30)
    private String containerType;

    @Lob
    @Column(name = "filename", nullable = false)
    private String filename;

    @Lob
    @Column(name = "disk_filename", nullable = false)
    private String diskFilename;

    @Column(name = "filesize", nullable = false)
    private Integer filesize;

    @Lob
    @Column(name = "content_type")
    private String contentType;

    @Column(name = "digest", nullable = false, length = 40)
    private String digest;

    @Column(name = "downloads", nullable = false)
    private Integer downloads;

    @Column(name = "author_id", nullable = false)
    private Integer authorId;

    @Lob
    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getFilesize() {
        return filesize;
    }

    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    public String getDiskFilename() {
        return diskFilename;
    }

    public void setDiskFilename(String diskFilename) {
        this.diskFilename = diskFilename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
    }

    public Long getContainerId() {
        return containerId;
    }

    public void setContainerId(Long containerId) {
        this.containerId = containerId;
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