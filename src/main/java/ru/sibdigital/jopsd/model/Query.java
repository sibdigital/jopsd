package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "queries", indexes = {
        @Index(name = "index_queries_on_project_id", columnList = "project_id"),
        @Index(name = "index_queries_on_user_id", columnList = "user_id")
})
@Entity
public class Query {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "filters")
    private String filters;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = false;

    @Lob
    @Column(name = "column_names")
    private String columnNames;

    @Lob
    @Column(name = "sort_criteria")
    private String sortCriteria;

    @Lob
    @Column(name = "group_by")
    private String groupBy;

    @Column(name = "display_sums", nullable = false)
    private Boolean displaySums = false;

    @Column(name = "timeline_visible")
    private Boolean timelineVisible;

    @Column(name = "show_hierarchies")
    private Boolean showHierarchies;

    @Column(name = "timeline_zoom_level")
    private Integer timelineZoomLevel;

    @Lob
    @Column(name = "timeline_labels")
    private String timelineLabels;

    @Lob
    @Column(name = "highlighting_mode")
    private String highlightingMode;

    @Lob
    @Column(name = "highlighted_attributes")
    private String highlightedAttributes;

    @Column(name = "hidden")
    private Boolean hidden;

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getHighlightedAttributes() {
        return highlightedAttributes;
    }

    public void setHighlightedAttributes(String highlightedAttributes) {
        this.highlightedAttributes = highlightedAttributes;
    }

    public String getHighlightingMode() {
        return highlightingMode;
    }

    public void setHighlightingMode(String highlightingMode) {
        this.highlightingMode = highlightingMode;
    }

    public String getTimelineLabels() {
        return timelineLabels;
    }

    public void setTimelineLabels(String timelineLabels) {
        this.timelineLabels = timelineLabels;
    }

    public Integer getTimelineZoomLevel() {
        return timelineZoomLevel;
    }

    public void setTimelineZoomLevel(Integer timelineZoomLevel) {
        this.timelineZoomLevel = timelineZoomLevel;
    }

    public Boolean getShowHierarchies() {
        return showHierarchies;
    }

    public void setShowHierarchies(Boolean showHierarchies) {
        this.showHierarchies = showHierarchies;
    }

    public Boolean getTimelineVisible() {
        return timelineVisible;
    }

    public void setTimelineVisible(Boolean timelineVisible) {
        this.timelineVisible = timelineVisible;
    }

    public Boolean getDisplaySums() {
        return displaySums;
    }

    public void setDisplaySums(Boolean displaySums) {
        this.displaySums = displaySums;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    public String getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String columnNames) {
        this.columnNames = columnNames;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}