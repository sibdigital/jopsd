package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;

// граф нужен для прекращения рекурсии
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "map-point-entity-graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "geographicMap")
                }
        )
})
@Table(name = "map_points")
@Entity
public class MapPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne()
    @JoinColumn(name = "work_package_id")
    private WorkPackage workPackage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "geographic_map_id", nullable = false)
    private GeographicMap geographicMap;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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

    public GeographicMap getGeographicMap() {
        return geographicMap;
    }

    public void setGeographicMap(GeographicMap geographicMap) {
        this.geographicMap = geographicMap;
    }
}
