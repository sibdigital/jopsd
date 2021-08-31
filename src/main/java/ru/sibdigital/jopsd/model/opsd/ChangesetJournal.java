package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "changeset_journals", indexes = {
        @Index(name = "index_changeset_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class ChangesetJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

    @Column(name = "repository_id", nullable = false)
    private Long repositoryId;

    @Column(name = "revision", nullable = false)
    private String revision;

    @Column(name = "committer")
    private String committer;

    @Column(name = "committed_on", nullable = false)
    private Timestamp committedOn;

    @Column(name = "comments")
    private String comments;

    @Column(name = "commit_date")
    private LocalDateTime commitDate;

    @Column(name = "scmid")
    private String scmid;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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