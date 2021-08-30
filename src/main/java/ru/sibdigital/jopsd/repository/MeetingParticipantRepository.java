package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.MeetingParticipant;

@Repository
public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipant, Long>, JpaSpecificationExecutor<MeetingParticipant> {
}