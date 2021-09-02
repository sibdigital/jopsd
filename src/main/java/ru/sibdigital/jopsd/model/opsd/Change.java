package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "changeset_id")
    private Changeset changeset;
    public Changeset getChangeset() {
        return changeset;
    }
    public void setChangeset(Changeset changeset) {
        this.changeset = changeset;
    }

    @Column(name = "action", nullable = false, length = 1)
    private String action;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "from_path")
    private String fromPath;

    @Column(name = "from_revision")
    private String fromRevision;

    @Column(name = "revision")
    private String revision;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}