package ru.sibdigital.jopsd.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "changesets", indexes = {
        @Index(name = "changesets_repos_rev", columnList = "repository_id, revision", unique = true),
        @Index(name = "index_changesets_on_repository_id", columnList = "repository_id"),
        @Index(name = "changesets_repos_scmid", columnList = "repository_id, scmid"),
        @Index(name = "index_changesets_on_repository_id_and_committed_on", columnList = "repository_id, committed_on"),
        @Index(name = "index_changesets_on_committed_on", columnList = "committed_on"),
        @Index(name = "index_changesets_on_user_id", columnList = "user_id")
})
@Entity
public class Changeset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "repository_id", nullable = false)
    private Long repositoryId;

    @Lob
    @Column(name = "revision", nullable = false)
    private String revision;

    @Lob
    @Column(name = "committer")
    private String committer;

    @Column(name = "committed_on", nullable = false)
    private Timestamp committedOn;

    @Lob
    @Column(name = "comments")
    private String comments;

    @Column(name = "commit_date")
    private LocalDateTime commitDate;

    @Lob
    @Column(name = "scmid")
    private String scmid;

    @Column(name = "user_id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getScmid() {
        return scmid;
    }

    public void setScmid(String scmid) {
        this.scmid = scmid;
    }

    public LocalDateTime getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(LocalDateTime commitDate) {
        this.commitDate = commitDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getCommittedOn() {
        return committedOn;
    }

    public void setCommittedOn(Timestamp committedOn) {
        this.committedOn = committedOn;
    }

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}