package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.CounterValue;

@Repository
public interface CounterValueRepository extends JpaRepository<CounterValue, Long>, JpaSpecificationExecutor<CounterValue> {
}