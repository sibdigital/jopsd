package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.WorkPackageTarget;

import java.util.List;

@RepositoryRestResource
public interface WorkPackageTargetRepo extends JpaRepository<WorkPackageTarget, Long>, JpaSpecificationExecutor<WorkPackageTarget> {
    List<WorkPackageTarget> findAllByWorkPackageIdAndTargetIdAndYear(Long workPackageId, Long targetId, Integer year);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(WorkPackageTarget workPackageTarget);
}
