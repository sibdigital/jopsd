package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "meetings", indexes = {
        @Index(name = "index_meetings_on_project_id_and_updated_at", columnList = "project_id, updated_at")
})
@Entity
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "author_id")
    private User author;
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "project_id")
    private Project project;
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "work_package_id")
    private WorkPackage workPackage;
    public WorkPackage getWorkPackage() {
        return workPackage;
    }
    public void setWorkPackage(WorkPackage workPackage) {
        this.workPackage = workPackage;
    }

    @Column(name = "title")
    private String title;

    @Column(name = "location")
    private String location;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "chairman_id")
    private Integer chairmanId;

    @Column(name = "add_participants")
    private String addParticipants;

    @Column(name = "speakers")
    private String speakers;

    @Column(name = "is_general")
    private Boolean isGeneral;

    public Boolean getIsGeneral() {
        return isGeneral;
    }

    public void setIsGeneral(Boolean isGeneral) {
        this.isGeneral = isGeneral;
    }

    public String getSpeakers() {
        return speakers;
    }

    public void setSpeakers(String speakers) {
        this.speakers = speakers;
    }

    public String getAddParticipants() {
        return addParticipants;
    }

    public void setAddParticipants(String addParticipants) {
        this.addParticipants = addParticipants;
    }

    public Integer getChairmanId() {
        return chairmanId;
    }

    public void setChairmanId(Integer chairmanId) {
        this.chairmanId = chairmanId;
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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}