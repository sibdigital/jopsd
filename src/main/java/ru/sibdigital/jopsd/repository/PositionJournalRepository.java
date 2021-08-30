package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.PositionJournal;

@Repository
public interface PositionJournalRepository extends JpaRepository<PositionJournal, Long>, JpaSpecificationExecutor<PositionJournal> {
}