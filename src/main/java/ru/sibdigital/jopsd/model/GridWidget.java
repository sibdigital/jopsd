package ru.sibdigital.jopsd.model;

import javax.persistence.*;

@Table(name = "grid_widgets", indexes = {
        @Index(name = "index_grid_widgets_on_grid_id", columnList = "grid_id")
})
@Entity
public class GridWidget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_row", nullable = false)
    private Integer startRow;

    @Column(name = "end_row", nullable = false)
    private Integer endRow;

    @Column(name = "start_column", nullable = false)
    private Integer startColumn;

    @Column(name = "end_column", nullable = false)
    private Integer endColumn;

    @Lob
    @Column(name = "identifier")
    private String identifier;

    @Lob
    @Column(name = "options")
    private String options;

    @Column(name = "grid_id")
    private Long gridId;

    public Long getGridId() {
        return gridId;
    }

    public void setGridId(Long gridId) {
        this.gridId = gridId;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(Integer endColumn) {
        this.endColumn = endColumn;
    }

    public Integer getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(Integer startColumn) {
        this.startColumn = startColumn;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}