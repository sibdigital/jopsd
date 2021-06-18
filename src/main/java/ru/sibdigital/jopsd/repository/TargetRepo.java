package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.Target;

import java.util.List;
import java.util.Optional;

@Repository
public interface TargetRepo extends JpaRepository<Target, Long> {
    Optional<Target> findTargetByMetaId(Long metaId);
    List<Target> findAllByProjectId(Long projectId);
}
