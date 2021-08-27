package ru.sibdigital.jopsd.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "attachments", indexes = {
        @Index(name = "index_attachments_on_fulltext_tsv", columnList = "fulltext_tsv"),
        @Index(name = "index_attachments_on_file_tsv", columnList = "file_tsv"),
        @Index(name = "index_attachments_on_author_id", columnList = "author_id"),
        @Index(name = "index_attachments_on_container_id_and_container_type", columnList = "container_id, container_type"),
        @Index(name = "index_attachments_on_created_at", columnList = "created_at")
})
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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
    private Long authorId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "file")
    private String file;

    @Lob
    @Column(name = "fulltext")
    private String fulltext;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "attach_type_id")
    private Long attachTypeId;

    @Column(name = "version")
    private Integer version;

    @Lob
    @Column(name = "some_href")
    private String someHref;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "user_locked_id")
    private Integer userLockedId;

    @Column(name = "fulltext_tsv")
    private String fulltextTsv;

    @Column(name = "file_tsv")
    private String fileTsv;

    public String getFileTsv() {
        return fileTsv;
    }

    public void setFileTsv(String fileTsv) {
        this.fileTsv = fileTsv;
    }

    public String getFulltextTsv() {
        return fulltextTsv;
    }

    public void setFulltextTsv(String fulltextTsv) {
        this.fulltextTsv = fulltextTsv;
    }

    public Integer getUserLockedId() {
        return userLockedId;
    }

    public void setUserLockedId(Integer userLockedId) {
        this.userLockedId = userLockedId;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getSomeHref() {
        return someHref;
    }

    public void setSomeHref(String someHref) {
        this.someHref = someHref;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getAttachTypeId() {
        return attachTypeId;
    }

    public void setAttachTypeId(Long attachTypeId) {
        this.attachTypeId = attachTypeId;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}