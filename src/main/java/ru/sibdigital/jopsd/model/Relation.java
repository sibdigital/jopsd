package ru.sibdigital.jopsd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "relations", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Relation {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "RELATIONS_GEN", sequenceName = "relations_id_seq", allocationSize = 1, schema = "public")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELATIONS_GEN")
    private Long id;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    private Long fromId;
    private Long toId;
    private Integer delay;
    private String description;
    private Integer hierarchy;
    private Integer relates;
    private Integer duplicates;
    private Integer blocks;
    private Integer follows;
    private Integer commonstart;
    private Integer commonfinish;
    private Integer includes;
    private Integer requires;
    private Integer count;

    @Basic
    @Column(name = "from_id")
    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    @Basic
    @Column(name = "to_id")
    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    @Basic
    @Column(name = "delay")
    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
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
    @Column(name = "hierarchy")
    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    @Basic
    @Column(name = "relates")
    public Integer getRelates() {
        return relates;
    }

    public void setRelates(Integer relates) {
        this.relates = relates;
    }

    @Basic
    @Column(name = "duplicates")
    public Integer getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(Integer duplicates) {
        this.duplicates = duplicates;
    }

    @Basic
    @Column(name = "blocks")
    public Integer getBlocks() {
        return blocks;
    }

    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }

    @Basic
    @Column(name = "follows")
    public Integer getFollows() {
        return follows;
    }

    public void setFollows(Integer follows) {
        this.follows = follows;
    }

    @Basic
    @Column(name = "commonstart")
    public Integer getCommonstart() {
        return commonstart;
    }

    public void setCommonstart(Integer commonstart) {
        this.commonstart = commonstart;
    }

    @Basic
    @Column(name = "commonfinish")
    public Integer getCommonfinish() {
        return commonfinish;
    }

    public void setCommonfinish(Integer commonfinish) {
        this.commonfinish = commonfinish;
    }

    @Basic
    @Column(name = "includes")
    public Integer getIncludes() {
        return includes;
    }

    public void setIncludes(Integer includes) {
        this.includes = includes;
    }

    @Basic
    @Column(name = "requires")
    public Integer getRequires() {
        return requires;
    }

    public void setRequires(Integer requires) {
        this.requires = requires;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Relation relations = (Relation) o;
        return Objects.equals(id, relations.id) && Objects.equals(fromId, relations.fromId) && Objects.equals(toId, relations.toId) && Objects.equals(delay, relations.delay) && Objects.equals(description, relations.description) && Objects.equals(hierarchy, relations.hierarchy) && Objects.equals(relates, relations.relates) && Objects.equals(duplicates, relations.duplicates) && Objects.equals(blocks, relations.blocks) && Objects.equals(follows, relations.follows) && Objects.equals(commonstart, relations.commonstart) && Objects.equals(commonfinish, relations.commonfinish) && Objects.equals(includes, relations.includes) && Objects.equals(requires, relations.requires) && Objects.equals(count, relations.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromId, toId, delay, description, hierarchy, relates, duplicates, blocks, follows, commonstart, commonfinish, includes, requires, count);
    }
}
