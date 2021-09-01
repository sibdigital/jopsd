package ru.sibdigital.jopsd.model.opsd;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Table(name = "customizable_journals", indexes = {
        @Index(name = "index_customizable_journals_on_custom_field_id", columnList = "custom_field_id"),
        @Index(name = "index_customizable_journals_on_journal_id", columnList = "journal_id")
})
@Entity
public class CustomizableJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "journal_id")
    private Journal journal;
    public Journal getJournal() {
        return journal;
    }
    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "custom_field_id")
    private CustomField customField;
    public CustomField getCustomField() {
        return customField;
    }
    public void setCustomField(CustomField customField) {
        this.customField = customField;
    }

    @Column(name = "value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}