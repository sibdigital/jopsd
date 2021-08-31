package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "auth_sources", indexes = {
        @Index(name = "index_auth_sources_on_id_and_type", columnList = "id, type")
})
@Entity
public class AuthSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "host", length = 60)
    private String host;

    @Column(name = "port")
    private Integer port;

    @Column(name = "account")
    private String account;

    @Column(name = "account_password")
    private String accountPassword;

    @Column(name = "base_dn")
    private String baseDn;

    @Column(name = "attr_login", length = 30)
    private String attrLogin;

    @Column(name = "attr_firstname", length = 30)
    private String attrFirstname;

    @Column(name = "attr_lastname", length = 30)
    private String attrLastname;

    @Column(name = "attr_mail", length = 30)
    private String attrMail;

    @Column(name = "onthefly_register", nullable = false)
    private Boolean ontheflyRegister = false;

    @Column(name = "attr_admin")
    private String attrAdmin;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "tls_mode", nullable = false)
    private Integer tlsMode;

    public Integer getTlsMode() {
        return tlsMode;
    }

    public void setTlsMode(Integer tlsMode) {
        this.tlsMode = tlsMode;
    }

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

    public String getAttrAdmin() {
        return attrAdmin;
    }

    public void setAttrAdmin(String attrAdmin) {
        this.attrAdmin = attrAdmin;
    }

    public Boolean getOntheflyRegister() {
        return ontheflyRegister;
    }

    public void setOntheflyRegister(Boolean ontheflyRegister) {
        this.ontheflyRegister = ontheflyRegister;
    }

    public String getAttrMail() {
        return attrMail;
    }

    public void setAttrMail(String attrMail) {
        this.attrMail = attrMail;
    }

    public String getAttrLastname() {
        return attrLastname;
    }

    public void setAttrLastname(String attrLastname) {
        this.attrLastname = attrLastname;
    }

    public String getAttrFirstname() {
        return attrFirstname;
    }

    public void setAttrFirstname(String attrFirstname) {
        this.attrFirstname = attrFirstname;
    }

    public String getAttrLogin() {
        return attrLogin;
    }

    public void setAttrLogin(String attrLogin) {
        this.attrLogin = attrLogin;
    }

    public String getBaseDn() {
        return baseDn;
    }

    public void setBaseDn(String baseDn) {
        this.baseDn = baseDn;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}