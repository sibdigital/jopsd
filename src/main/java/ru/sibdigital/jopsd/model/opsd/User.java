package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "organization_id")
    private Organization organization;
    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "position_id")
    private Position position;
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "direct_manager_id")
    private User directManager;
    public User getDirectManager() {
        return directManager;
    }
    public void setDirectManager(User directManager) {
        this.directManager = directManager;
    }

    @Column(name = "login", nullable = false, length = 256)
    private String login;

    @Column(name = "firstname", nullable = false, length = 30)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 30)
    private String lastname;

    @Column(name = "mail", nullable = false, length = 60)
    private String mail;

    @Column(name = "admin", nullable = false)
    private Boolean admin = false;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "last_login_on")
    private Timestamp lastLoginOn;

    @Column(name = "language", length = 5)
    private String language;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "type")
    private String type;

    @Column(name = "identity_url")
    private String identityUrl;

    @Column(name = "mail_notification", nullable = false)
    private String mailNotification;

    @Column(name = "first_login", nullable = false)
    private Boolean firstLogin = false;

    @Column(name = "force_password_change")
    private Boolean forcePasswordChange;

    @Column(name = "failed_login_count")
    private Integer failedLoginCount;

    @Column(name = "last_failed_login_on")
    private Timestamp lastFailedLoginOn;

    @Column(name = "consented_at")
    private Timestamp consentedAt;

    @Column(name = "patronymic", length = 30)
    private String patronymic;

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

    @Column(name = "last_ip")
    private String lastIp;

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Timestamp getConsentedAt() {
        return consentedAt;
    }

    public void setConsentedAt(Timestamp consentedAt) {
        this.consentedAt = consentedAt;
    }

    public Timestamp getLastFailedLoginOn() {
        return lastFailedLoginOn;
    }

    public void setLastFailedLoginOn(Timestamp lastFailedLoginOn) {
        this.lastFailedLoginOn = lastFailedLoginOn;
    }

    public Integer getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(Integer failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public Boolean getForcePasswordChange() {
        return forcePasswordChange;
    }

    public void setForcePasswordChange(Boolean forcePasswordChange) {
        this.forcePasswordChange = forcePasswordChange;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getMailNotification() {
        return mailNotification;
    }

    public void setMailNotification(String mailNotification) {
        this.mailNotification = mailNotification;
    }

    public String getIdentityUrl() {
        return identityUrl;
    }

    public void setIdentityUrl(String identityUrl) {
        this.identityUrl = identityUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Timestamp getLastLoginOn() {
        return lastLoginOn;
    }

    public void setLastLoginOn(Timestamp lastLoginOn) {
        this.lastLoginOn = lastLoginOn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}