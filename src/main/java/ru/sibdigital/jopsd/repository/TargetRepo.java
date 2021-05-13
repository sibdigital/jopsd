package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.Target;

@Repository
public interface TargetRepo extends JpaRepository<Target, Long> {

}
