package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "member_roles", indexes = {
        @Index(name = "index_member_roles_on_role_id", columnList = "role_id"),
        @Index(name = "index_member_roles_on_member_id", columnList = "member_id"),
        @Index(name = "index_member_roles_on_inherited_from", columnList = "inherited_from")
})
@Entity
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}