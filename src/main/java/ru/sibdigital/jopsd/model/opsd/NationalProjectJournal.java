package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "national_project_journals", indexes = {
        @Index(name = "index_national_project_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class NationalProjectJournal {
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
    @JoinColumn(name = "parent_id")
    private NationalProject parent;
    public NationalProject getParent() {
        return parent;
    }
    public void setParent(NationalProject parent) {
        this.parent = parent;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "leader")
    private String leader;

    @Column(name = "leader_position")
    private String leaderPosition;

    @Column(name = "type")
    private String type;

    @Column(name = "curator")
    private String curator;

    @Column(name = "curator_position")
    private String curatorPosition;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "due_date")
    private Timestamp dueDate;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getCuratorPosition() {
        return curatorPosition;
    }

    public void setCuratorPosition(String curatorPosition) {
        this.curatorPosition = curatorPosition;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLeaderPosition() {
        return leaderPosition;
    }

    public void setLeaderPosition(String leaderPosition) {
        this.leaderPosition = leaderPosition;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}