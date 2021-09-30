package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.TargetExecutionValue;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface TargetExecutionValueRepo extends JpaRepository<TargetExecutionValue, Long>, JpaSpecificationExecutor<TargetExecutionValue> {
    Optional<TargetExecutionValue> findTargetExecutionValueByTargetIdAndYearAndQuarter(Long targetId, Integer year, Integer quarter);
    List<TargetExecutionValue> findAllByTargetIdAndYear(Long targetId, Integer year);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(TargetExecutionValue targetExecutionValue);
}
