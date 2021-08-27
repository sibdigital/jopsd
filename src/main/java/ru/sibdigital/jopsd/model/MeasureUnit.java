package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "measure_units")
@Entity
public class MeasureUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "short_name")
    private String shortName;

    @Lob
    @Column(name = "okei_code")
    private String okeiCode;

    public String getOkeiCode() {
        return okeiCode;
    }

    public void setOkeiCode(String okeiCode) {
        this.okeiCode = okeiCode;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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