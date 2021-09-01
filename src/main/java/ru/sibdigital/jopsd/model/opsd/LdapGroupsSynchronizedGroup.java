package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "group_id")
    private User group;
    public User getGroup() {
        return group;
    }
    public void setGroup(User group) {
        this.group = group;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "auth_source_id")
    private AuthSource authSource;
    public AuthSource getAuthSource() {
        return authSource;
    }
    public void setAuthSource(AuthSource authSource) {
        this.authSource = authSource;
    }

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