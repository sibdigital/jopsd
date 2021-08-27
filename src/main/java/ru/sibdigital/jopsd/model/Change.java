package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "changes", indexes = {
        @Index(name = "changesets_changeset_id", columnList = "changeset_id")
})
@Entity
public class Change {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "changeset_id", nullable = false)
    private Long changesetId;

    @Column(name = "action", nullable = false, length = 1)
    private String action;

    @Lob
    @Column(name = "path", nullable = false)
    private String path;

    @Lob
    @Column(name = "from_path")
    private String fromPath;

    @Lob
    @Column(name = "from_revision")
    private String fromRevision;

    @Lob
    @Column(name = "revision")
    private String revision;

    @Lob
    @Column(name = "branch")
    private String branch;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getFromRevision() {
        return fromRevision;
    }

    public void setFromRevision(String fromRevision) {
        this.fromRevision = fromRevision;
    }

    public String getFromPath() {
        return fromPath;
    }

    public void setFromPath(String fromPath) {
        this.fromPath = fromPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getChangesetId() {
        return changesetId;
    }

    public void setChangesetId(Long changesetId) {
        this.changesetId = changesetId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}