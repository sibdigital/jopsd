package ru.sibdigital.jopsd.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "custom_styles")
@Entity
public class CustomStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "logo")
    private String logo;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Lob
    @Column(name = "favicon")
    private String favicon;

    @Lob
    @Column(name = "touch_icon")
    private String touchIcon;

    public String getTouchIcon() {
        return touchIcon;
    }

    public void setTouchIcon(String touchIcon) {
        this.touchIcon = touchIcon;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}