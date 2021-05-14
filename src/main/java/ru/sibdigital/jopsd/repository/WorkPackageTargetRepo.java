package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.WorkPackageTarget;

@Repository
public interface WorkPackageTargetRepo extends JpaRepository<WorkPackageTarget, Long> {

}
