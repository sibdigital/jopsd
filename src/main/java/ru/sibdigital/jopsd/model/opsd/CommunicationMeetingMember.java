package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "communication_meeting_members")
@Entity
public class CommunicationMeetingMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "stakeholder_id")
    private Long stakeholderId;

    @Column(name = "communication_meeting_id")
    private Long communicationMeetingId;

    @Column(name = "stakeholder_type")
    private String stakeholderType;

    public String getStakeholderType() {
        return stakeholderType;
    }

    public void setStakeholderType(String stakeholderType) {
        this.stakeholderType = stakeholderType;
    }

    public Long getCommunicationMeetingId() {
        return communicationMeetingId;
    }

    public void setCommunicationMeetingId(Long communicationMeetingId) {
        this.communicationMeetingId = communicationMeetingId;
    }

    public Long getStakeholderId() {
        return stakeholderId;
    }

    public void setStakeholderId(Long stakeholderId) {
        this.stakeholderId = stakeholderId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}