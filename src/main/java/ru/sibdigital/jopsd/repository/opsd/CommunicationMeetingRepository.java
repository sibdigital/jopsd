package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.CommunicationMeeting;

@RepositoryRestResource
public interface CommunicationMeetingRepository extends JpaRepository<CommunicationMeeting, Long> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(CommunicationMeeting communicationMeeting);
}