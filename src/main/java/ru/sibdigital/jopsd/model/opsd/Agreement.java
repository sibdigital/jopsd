package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "agreements")
@Entity
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    @ManyToOne
    @JoinColumn(name = "national_project_id")
    private NationalProject nationalProject;
    public NationalProject getNationalProject() {
        return nationalProject;
    }
    public void setNationalProject(NationalProject nationalProject) {
        this.nationalProject = nationalProject;
    }

    @ManyToOne
    @JoinColumn(name = "federal_project_id")
    private NationalProject federalProject;
    public NationalProject getFederalProject() {
        return federalProject;
    }
    public void setFederalProject(NationalProject federalProject) {
        this.federalProject = federalProject;
    }

    @Column(name = "date_agreement")
    private LocalDateTime dateAgreement;

    @Column(name = "number_agreement")
    private String numberAgreement;

    @Column(name = "count_days")
    private Integer countDays;

    @Column(name = "state_program")
    private String stateProgram;

    @Column(name = "other_liabilities_2141")
    private String otherLiabilities2141;

    @Column(name = "other_liabilities_2142")
    private String otherLiabilities2142;

    @Column(name = "other_liabilities_2281")
    private String otherLiabilities2281;

    @Column(name = "other_liabilities_2282")
    private String otherLiabilities2282;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

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

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getOtherLiabilities2282() {
        return otherLiabilities2282;
    }

    public void setOtherLiabilities2282(String otherLiabilities2282) {
        this.otherLiabilities2282 = otherLiabilities2282;
    }

    public String getOtherLiabilities2281() {
        return otherLiabilities2281;
    }

    public void setOtherLiabilities2281(String otherLiabilities2281) {
        this.otherLiabilities2281 = otherLiabilities2281;
    }

    public String getOtherLiabilities2142() {
        return otherLiabilities2142;
    }

    public void setOtherLiabilities2142(String otherLiabilities2142) {
        this.otherLiabilities2142 = otherLiabilities2142;
    }

    public String getOtherLiabilities2141() {
        return otherLiabilities2141;
    }

    public void setOtherLiabilities2141(String otherLiabilities2141) {
        this.otherLiabilities2141 = otherLiabilities2141;
    }

    public String getStateProgram() {
        return stateProgram;
    }

    public void setStateProgram(String stateProgram) {
        this.stateProgram = stateProgram;
    }

    public Integer getCountDays() {
        return countDays;
    }

    public void setCountDays(Integer countDays) {
        this.countDays = countDays;
    }

    public String getNumberAgreement() {
        return numberAgreement;
    }

    public void setNumberAgreement(String numberAgreement) {
        this.numberAgreement = numberAgreement;
    }

    public LocalDateTime getDateAgreement() {
        return dateAgreement;
    }

    public void setDateAgreement(LocalDateTime dateAgreement) {
        this.dateAgreement = dateAgreement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}