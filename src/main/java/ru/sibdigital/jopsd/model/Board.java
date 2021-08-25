package ru.sibdigital.jopsd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "boards", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Board {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "BOARDS_GEN", sequenceName = "boards_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARDS_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    private Long projectId;
    private String name;
    private String description;
    private Integer position;
    private Integer topicsCount;
    private Integer messagesCount;
    private Long lastMessageId;
    private Boolean isDefault;


    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "project_id")
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    @Column(name = "topics_count")
    public Integer getTopicsCount() {
        return topicsCount;
    }

    public void setTopicsCount(Integer topicsCount) {
        this.topicsCount = topicsCount;
    }

    @Basic
    @Column(name = "messages_count")
    public Integer getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(Integer messagesCount) {
        this.messagesCount = messagesCount;
    }

    @Basic
    @Column(name = "last_message_id")
    public Long getLastMessageId() {
        return lastMessageId;
    }

    public void setLastMessageId(Long lastMessageId) {
        this.lastMessageId = lastMessageId;
    }

    @Basic
    @Column(name = "is_default")
    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(id, board.id) && Objects.equals(projectId, board.projectId) && Objects.equals(name, board.name) && Objects.equals(description, board.description) && Objects.equals(position, board.position) && Objects.equals(topicsCount, board.topicsCount) && Objects.equals(messagesCount, board.messagesCount) && Objects.equals(lastMessageId, board.lastMessageId) && Objects.equals(isDefault, board.isDefault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, name, description, position, topicsCount, messagesCount, lastMessageId, isDefault);
    }
}
