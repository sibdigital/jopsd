package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.Target;

import java.util.List;
import java.util.Optional;

@Repository
public interface TargetRepo extends JpaRepository<Target, Long>, JpaSpecificationExecutor<Target> {
    Optional<Target> findTargetByMetaId(Long metaId);
    List<Target> findAllByProjectId(Long projectId);
    Page<Target> findAllByProject_Id(Long projectId, Pageable pageable);
    Page<Target> findByProject_IdAndNameContainingIgnoreCase(Long projectId, String name, Pageable pageable);
}
