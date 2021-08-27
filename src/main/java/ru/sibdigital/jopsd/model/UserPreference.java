package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "user_preferences", indexes = {
        @Index(name = "index_user_preferences_on_user_id", columnList = "user_id")
})
@Entity
public class UserPreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Lob
    @Column(name = "others")
    private String others;

    @Column(name = "hide_mail")
    private Boolean hideMail;

    @Lob
    @Column(name = "time_zone")
    private String timeZone;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getHideMail() {
        return hideMail;
    }

    public void setHideMail(Boolean hideMail) {
        this.hideMail = hideMail;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}