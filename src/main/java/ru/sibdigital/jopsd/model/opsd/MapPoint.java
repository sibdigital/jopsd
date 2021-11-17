package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

@Table(name = "map_points")
@Entity
public class MapPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_work_package", nullable = false)
    private WorkPackage workPackage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_project", nullable = false)
    private Project project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_map", nullable = false)
    private GMap gMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public WorkPackage getWorkPackage() {
        return workPackage;
    }

    public void setWorkPackage(WorkPackage workPackage) {
        this.workPackage = workPackage;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public GMap getgMap() {
        return gMap;
    }

    public void setgMap(GMap gMap) {
        this.gMap = gMap;
    }
}