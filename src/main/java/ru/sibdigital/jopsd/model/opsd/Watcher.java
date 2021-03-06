package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Table(name = "watchers", indexes = {
        @Index(name = "watchers_user_id_type", columnList = "user_id, watchable_type"),
        @Index(name = "index_watchers_on_user_id", columnList = "user_id"),
        @Index(name = "index_watchers_on_watchable_id_and_watchable_type", columnList = "watchable_id, watchable_type")
})
@Entity
public class Watcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "watchable_type", nullable = false)
    private String watchableType;

    @Column(name = "watchable_id", nullable = false)
    private Long watchableId;

    public Long getWatchableId() {
        return watchableId;
    }

    public void setWatchableId(Long watchableId) {
        this.watchableId = watchableId;
    }

    public String getWatchableType() {
        return watchableType;
    }

    public void setWatchableType(String watchableType) {
        this.watchableType = watchableType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}