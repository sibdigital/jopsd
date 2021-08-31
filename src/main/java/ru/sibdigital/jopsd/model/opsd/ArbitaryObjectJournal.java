package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "arbitary_object_journals", indexes = {
        @Index(name = "index_arbitary_object_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class ArbitaryObjectJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "journal_id")
    private Journal journal;
    public Journal getJournal() {
        return journal;
    }
    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Enumeration type;
    public Enumeration getType() {
        return type;
    }
    public void setType(Enumeration type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "is_approve")
    private Boolean isApprove;

    public Boolean getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Boolean isApprove) {
        this.isApprove = isApprove;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}