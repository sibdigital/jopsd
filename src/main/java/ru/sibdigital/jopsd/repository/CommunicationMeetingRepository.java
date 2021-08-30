package ru.sibdigital.jopsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.CommunicationMeeting;

@Repository
public interface CommunicationMeetingRepository extends JpaRepository<CommunicationMeeting, Long> {
}