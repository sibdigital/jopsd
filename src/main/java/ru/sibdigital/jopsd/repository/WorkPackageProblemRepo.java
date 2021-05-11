package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.WorkPackageProblem;

@Repository
public interface WorkPackageProblemRepo extends JpaRepository<WorkPackageProblem, Long> {

}
