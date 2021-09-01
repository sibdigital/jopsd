package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    @JoinColumn(name = "member_id")
    private Member member;
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "inherited_from")
    private Integer inheritedFrom;

    public Integer getInheritedFrom() {
        return inheritedFrom;
    }

    public void setInheritedFrom(Integer inheritedFrom) {
        this.inheritedFrom = inheritedFrom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}