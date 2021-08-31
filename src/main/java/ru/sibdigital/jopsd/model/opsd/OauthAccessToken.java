package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "oauth_access_tokens", indexes = {
        @Index(name = "index_oauth_access_tokens_on_resource_owner_id", columnList = "resource_owner_id"),
        @Index(name = "index_oauth_access_tokens_on_token", columnList = "token", unique = true),
        @Index(name = "index_oauth_access_tokens_on_application_id", columnList = "application_id"),
        @Index(name = "index_oauth_access_tokens_on_refresh_token", columnList = "refresh_token", unique = true)
})
@Entity
public class OauthAccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "resource_owner_id")
    private Long resourceOwnerId;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private OauthApplication application;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expires_in")
    private Integer expiresIn;

    @Column(name = "revoked_at")
    private Timestamp revokedAt;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "scopes")
    private String scopes;

    @Column(name = "previous_refresh_token", nullable = false)
    private String previousRefreshToken;

    public String getPreviousRefreshToken() {
        return previousRefreshToken;
    }

    public void setPreviousRefreshToken(String previousRefreshToken) {
        this.previousRefreshToken = previousRefreshToken;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getRevokedAt() {
        return revokedAt;
    }

    public void setRevokedAt(Timestamp revokedAt) {
        this.revokedAt = revokedAt;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OauthApplication getApplication() {
        return application;
    }

    public void setApplication(OauthApplication application) {
        this.application = application;
    }

    public Long getResourceOwnerId() {
        return resourceOwnerId;
    }

    public void setResourceOwnerId(Long resourceOwnerId) {
        this.resourceOwnerId = resourceOwnerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}