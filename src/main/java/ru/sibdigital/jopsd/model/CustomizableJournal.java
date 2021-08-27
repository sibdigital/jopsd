package ru.sibdigital.jopsd.model;

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

    @Column(name = "journal_id", nullable = false)
    private Long journalId;

    @Column(name = "custom_field_id", nullable = false)
    private Integer customFieldId;

    @Lob
    @Column(name = "value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(Integer customFieldId) {
        this.customFieldId = customFieldId;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}