package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "organizations")
@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private String inn;

    @Column(name = "is_legal_entity")
    private Boolean isLegalEntity;

    @Column(name = "org_type")
    private Integer orgType;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "is_approve")
    private Boolean isApprove;

    @Column(name = "org_prav_forma")
    private String orgPravForma;

    @Column(name = "ur_addr")
    private String urAddr;

    @Column(name = "post_addr")
    private String postAddr;

    @Column(name = "otrasl")
    private String otrasl;

    @Column(name = "gorod")
    private String gorod;

    @Column(name = "capital")
    private String capital;

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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getGorod() {
        return gorod;
    }

    public void setGorod(String gorod) {
        this.gorod = gorod;
    }

    public String getOtrasl() {
        return otrasl;
    }

    public void setOtrasl(String otrasl) {
        this.otrasl = otrasl;
    }

    public String getPostAddr() {
        return postAddr;
    }

    public void setPostAddr(String postAddr) {
        this.postAddr = postAddr;
    }

    public String getUrAddr() {
        return urAddr;
    }

    public void setUrAddr(String urAddr) {
        this.urAddr = urAddr;
    }

    public String getOrgPravForma() {
        return orgPravForma;
    }

    public void setOrgPravForma(String orgPravForma) {
        this.orgPravForma = orgPravForma;
    }

    public Boolean getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Boolean isApprove) {
        this.isApprove = isApprove;
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

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public Boolean getIsLegalEntity() {
        return isLegalEntity;
    }

    public void setIsLegalEntity(Boolean isLegalEntity) {
        this.isLegalEntity = isLegalEntity;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}