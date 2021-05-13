package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.WorkPackage;

@Repository
public interface WorkPackageRepo extends JpaRepository<WorkPackage, Long> {
}
