package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    private Organization parent;
    public Organization getParent() {
        return parent;
    }
    public void setParent(Organization parent) {
        this.parent = parent;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}