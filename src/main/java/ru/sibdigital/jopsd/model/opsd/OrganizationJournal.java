package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "organization_journals", indexes = {
        @Index(name = "index_organization_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class OrganizationJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

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

    @Column(name = "is_approve")
    private Boolean isApprove;

    public Boolean getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Boolean isApprove) {
        this.isApprove = isApprove;
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