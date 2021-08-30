package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.TargetExecutionValue;

import java.util.List;
import java.util.Optional;

// TODO Добавить класс в master
@Repository
public interface TargetExecutionValueRepo extends JpaRepository<TargetExecutionValue, Long>, JpaSpecificationExecutor<TargetExecutionValue> {
    Optional<TargetExecutionValue> findTargetExecutionValueByTargetIdAndYearAndQuarter(Long targetId, Integer year, Integer quarter);
    List<TargetExecutionValue> findAllByTargetIdAndYear(Long targetId, Integer year);
}
