package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Status;

import java.util.List;
import java.util.Map;

@RepositoryRestResource
public interface StatusRepository extends JpaRepository<Status, Long>, JpaSpecificationExecutor<Status> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(Status status);

    @Query(value = "SELECT name, count(subject) " +
            "FROM statuses " +
            "inner join work_packages on project_id =:id " +
            "AND statuses.id = status_id group by name"
            , nativeQuery = true)
    List<Map<String, Object>> findCountWorkPackagesByProjectId(Long id);
}