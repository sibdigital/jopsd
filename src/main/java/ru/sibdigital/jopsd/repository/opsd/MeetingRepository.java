package ru.sibdigital.jopsd.repository.opsd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.sibdigital.jopsd.model.opsd.Meeting;

import java.util.List;

@RepositoryRestResource
public interface MeetingRepository extends JpaRepository<Meeting, Long>, JpaSpecificationExecutor<Meeting> {
    @Override
    @RestResource(exported = false)
    void deleteById(Long id);


    @Override
    @RestResource(exported = false)
    void delete(Meeting meeting);

    @Query(value = "SELECT * FROM meetings " +
            "inner join members ON members.project_id = meetings.project_id AND user_id =:id " +
            "WHERE current_timestamp > start_time"
            , nativeQuery = true)
    List<Meeting> findExpiredMeetingsByUserId(@Param("id") Long id);

    @Query(value = "SELECT * FROM meetings " +
            "WHERE project_id =:id " +
            "AND current_timestamp > start_time"
            , nativeQuery = true)
    List<Meeting> findExpiredMeetingsByProjectId(Long id);

    @Query(value = "SELECT * FROM meetings " +
            "WHERE project_id =:id " +
            "AND cast(start_time as date) - CURRENT_DATE >= 14"
            , nativeQuery = true)
    List <Meeting> findMeetingsOverDays(Long id);

}