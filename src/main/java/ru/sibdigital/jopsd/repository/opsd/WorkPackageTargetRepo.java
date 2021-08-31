package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.WorkPackageTarget;

import java.util.List;

@Repository
public interface WorkPackageTargetRepo extends JpaRepository<WorkPackageTarget, Long>, JpaSpecificationExecutor<WorkPackageTarget> {
    List<WorkPackageTarget> findAllByWorkPackageIdAndTargetIdAndYear(Long workPackageId, Long targetId, Integer year);
}
