package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sibdigital.jopsd.model.opsd.CommunicationMeeting;

@Repository
public interface CommunicationMeetingRepository extends JpaRepository<CommunicationMeeting, Long> {
}