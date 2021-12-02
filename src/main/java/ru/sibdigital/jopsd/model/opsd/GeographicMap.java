package ru.sibdigital.jopsd.model.opsd;

import javax.persistence.*;
import java.util.List;

@Table(name = "geographic_maps")
@Entity
public class GeographicMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "geographicMap", fetch = FetchType.EAGER)
    private List<MapPoint> mapPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<MapPoint> getMapPoints() {
        return mapPoints;
    }

    public void setMapPoints(List<MapPoint> mapPoints) {
        this.mapPoints = mapPoints;
    }
}
