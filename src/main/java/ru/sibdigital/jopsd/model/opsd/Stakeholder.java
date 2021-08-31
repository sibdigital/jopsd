package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "stakeholders")
@Entity
public class Stakeholder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "phone_wrk")
    private String phoneWrk;

    @Column(name = "phone_wrk_add")
    private String phoneWrkAdd;

    @Column(name = "phone_mobile")
    private String phoneMobile;

    @Column(name = "mail_add")
    private String mailAdd;

    @Column(name = "address")
    private String address;

    @Column(name = "cabinet")
    private String cabinet;

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

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMailAdd() {
        return mailAdd;
    }

    public void setMailAdd(String mailAdd) {
        this.mailAdd = mailAdd;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getPhoneWrkAdd() {
        return phoneWrkAdd;
    }

    public void setPhoneWrkAdd(String phoneWrkAdd) {
        this.phoneWrkAdd = phoneWrkAdd;
    }

    public String getPhoneWrk() {
        return phoneWrk;
    }

    public void setPhoneWrk(String phoneWrk) {
        this.phoneWrk = phoneWrk;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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