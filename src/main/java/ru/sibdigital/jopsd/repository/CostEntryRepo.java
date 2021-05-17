package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.CostEntry;

@Repository
public interface CostEntryRepo extends JpaRepository<CostEntry, Long> {

}
