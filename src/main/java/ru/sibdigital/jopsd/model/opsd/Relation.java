package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "from_id")
    private WorkPackage from;
    public WorkPackage getFrom() {
        return from;
    }
    public void setFrom(WorkPackage from) {
        this.from = from;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "to_id")
    private WorkPackage to;
    public WorkPackage getTo() {
        return to;
    }
    public void setTo(WorkPackage to) {
        this.to = to;
    }

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

}
