package ru.sibdigital.jopsd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "projects_types", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@IdClass(ProjectType.ProjectTypePK.class)
public class ProjectType {

    static class ProjectTypePK implements Serializable {
        protected Long projectId;
        protected Long typeId;

        public ProjectTypePK() {}

        public ProjectTypePK(Long projectId, Long typeId) {
            this.projectId = projectId;
            this.typeId = typeId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProjectTypePK that = (ProjectTypePK) o;
            return Objects.equals(projectId, that.projectId) && Objects.equals(typeId, that.typeId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(projectId, typeId);
        }
    }

    @Id
    private Long projectId;

    @Id
    private Long typeId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
