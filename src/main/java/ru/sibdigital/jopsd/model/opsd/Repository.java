package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "repositories", indexes = {
        @Index(name = "index_repositories_on_project_id", columnList = "project_id")
})
@Entity
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "project_id")
    private Project project;
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "login", length = 60)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "root_url")
    private String rootUrl;

    @Column(name = "type")
    private String type;

    @Column(name = "path_encoding", length = 64)
    private String pathEncoding;

    @Column(name = "log_encoding", length = 64)
    private String logEncoding;

    @Column(name = "scm_type", nullable = false)
    private String scmType;

    @Column(name = "required_storage_bytes", nullable = false)
    private Long requiredStorageBytes;

    @Column(name = "storage_updated_at")
    private Timestamp storageUpdatedAt;

    public Timestamp getStorageUpdatedAt() {
        return storageUpdatedAt;
    }

    public void setStorageUpdatedAt(Timestamp storageUpdatedAt) {
        this.storageUpdatedAt = storageUpdatedAt;
    }

    public Long getRequiredStorageBytes() {
        return requiredStorageBytes;
    }

    public void setRequiredStorageBytes(Long requiredStorageBytes) {
        this.requiredStorageBytes = requiredStorageBytes;
    }

    public String getScmType() {
        return scmType;
    }

    public void setScmType(String scmType) {
        this.scmType = scmType;
    }

    public String getLogEncoding() {
        return logEncoding;
    }

    public void setLogEncoding(String logEncoding) {
        this.logEncoding = logEncoding;
    }

    public String getPathEncoding() {
        return pathEncoding;
    }

    public void setPathEncoding(String pathEncoding) {
        this.pathEncoding = pathEncoding;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}