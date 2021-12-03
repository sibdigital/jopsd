package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Risk;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;
import ru.sibdigital.jopsd.model.opsd.WorkPackageProblem;

import java.util.List;

@RepositoryRestResource
public interface WorkPackageProblemRepo extends JpaRepository<WorkPackageProblem, Long>, JpaSpecificationExecutor<WorkPackageProblem> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(WorkPackageProblem workPackageProblem);

    WorkPackageProblem findByWorkPackageAndRisk(WorkPackage workPackage, Risk risk);

    List<WorkPackageProblem> findAllByWorkPackage(WorkPackage workPackage);
}
