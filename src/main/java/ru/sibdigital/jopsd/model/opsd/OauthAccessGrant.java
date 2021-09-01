package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "oauth_access_grants", indexes = {
        @Index(name = "index_oauth_access_grants_on_application_id", columnList = "application_id"),
        @Index(name = "index_oauth_access_grants_on_token", columnList = "token", unique = true),
        @Index(name = "index_oauth_access_grants_on_resource_owner_id", columnList = "resource_owner_id")
})
@Entity
public class OauthAccessGrant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "resource_owner_id", nullable = false)
    private Long resourceOwnerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private OauthApplication application;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "expires_in", nullable = false)
    private Integer expiresIn;

    @Column(name = "redirect_uri", nullable = false)
    private String redirectUri;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "revoked_at")
    private Timestamp revokedAt;

    @Column(name = "scopes")
    private String scopes;

    @Column(name = "code_challenge")
    private String codeChallenge;

    @Column(name = "code_challenge_method")
    private String codeChallengeMethod;

    public String getCodeChallengeMethod() {
        return codeChallengeMethod;
    }

    public void setCodeChallengeMethod(String codeChallengeMethod) {
        this.codeChallengeMethod = codeChallengeMethod;
    }

    public String getCodeChallenge() {
        return codeChallenge;
    }

    public void setCodeChallenge(String codeChallenge) {
        this.codeChallenge = codeChallenge;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public Timestamp getRevokedAt() {
        return revokedAt;
    }

    public void setRevokedAt(Timestamp revokedAt) {
        this.revokedAt = revokedAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
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