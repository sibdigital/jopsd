package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "ldap_groups_synchronized_groups", indexes = {
        @Index(name = "index_ldap_groups_synchronized_groups_on_group_id", columnList = "group_id"),
        @Index(name = "index_ldap_groups_synchronized_groups_on_auth_source_id", columnList = "auth_source_id")
})
@Entity
public class LdapGroupsSynchronizedGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "entry")
    private String entry;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "auth_source_id")
    private Long authSourceId;

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

    public Long getAuthSourceId() {
        return authSourceId;
    }

    public void setAuthSourceId(Long authSourceId) {
        this.authSourceId = authSourceId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}