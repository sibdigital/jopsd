package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "delayed_jobs", indexes = {
        @Index(name = "delayed_jobs_priority", columnList = "priority, run_at")
})
@Entity
public class DelayedJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "attempts")
    private Integer attempts;

    @Column(name = "handler")
    private String handler;

    @Column(name = "last_error")
    private String lastError;

    @Column(name = "run_at")
    private Timestamp runAt;

    @Column(name = "locked_at")
    private Timestamp lockedAt;

    @Column(name = "failed_at")
    private Timestamp failedAt;

    @Column(name = "locked_by")
    private String lockedBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "queue")
    private String queue;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

    public Timestamp getFailedAt() {
        return failedAt;
    }

    public void setFailedAt(Timestamp failedAt) {
        this.failedAt = failedAt;
    }

    public Timestamp getLockedAt() {
        return lockedAt;
    }

    public void setLockedAt(Timestamp lockedAt) {
        this.lockedAt = lockedAt;
    }

    public Timestamp getRunAt() {
        return runAt;
    }

    public void setRunAt(Timestamp runAt) {
        this.runAt = runAt;
    }

    public String getLastError() {
        return lastError;
    }

    public void setLastError(String lastError) {
        this.lastError = lastError;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}