package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "member_role_journals", indexes = {
        @Index(name = "index_member_role_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class MemberRoleJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "inherited_from")
    private Integer inheritedFrom;

    public Integer getInheritedFrom() {
        return inheritedFrom;
    }

    public void setInheritedFrom(Integer inheritedFrom) {
        this.inheritedFrom = inheritedFrom;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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