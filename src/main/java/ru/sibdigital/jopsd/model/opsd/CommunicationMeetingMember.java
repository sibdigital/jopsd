package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Table(name = "communication_meeting_members")
@Entity
public class CommunicationMeetingMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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
    @JoinColumn(name = "stakeholder_id")
    private Stakeholder stakeholder;
    public Stakeholder getStakeholder() {
        return stakeholder;
    }
    public void setStakeholder(Stakeholder stakeholder) {
        this.stakeholder = stakeholder;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "communication_meeting_id")
    private CommunicationMeeting communicationMeeting;
    public CommunicationMeeting getCommunicationMeeting() {
        return communicationMeeting;
    }
    public void setCommunicationMeeting(CommunicationMeeting communicationMeeting) {
        this.communicationMeeting = communicationMeeting;
    }

    @Column(name = "stakeholder_type")
    private String stakeholderType;

    public String getStakeholderType() {
        return stakeholderType;
    }

    public void setStakeholderType(String stakeholderType) {
        this.stakeholderType = stakeholderType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}