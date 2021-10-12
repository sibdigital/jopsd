package ru.sibdigital.jopsd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.model.opsd.Meeting;
import ru.sibdigital.jopsd.repository.opsd.MeetingRepository;

import java.util.List;
import java.util.Map;

@Controller
public class MeetingController extends SuperController{

    @Autowired
    private MeetingRepository meetingRepository;



    @GetMapping("/meetings/expired_meeting_users/{id}")
    public @ResponseBody List <Meeting> getExpiredMeetingsByUserId(@PathVariable ("id") Long id) {
        return meetingRepository.findExpiredMeetingsByUserId(id);
    }

    @GetMapping("/meetings/expired_meeting_projects/{id}")
    public @ResponseBody List <Meeting> getExpiredMeetingsByProjectId(@PathVariable ("id") Long id) {
        return meetingRepository.findExpiredMeetingsByProjectId(id);
    }

    @GetMapping("/meetings/meeting_over_days/{id}")
    public @ResponseBody List <Meeting> getMeetingsOverDaysByProjectId(@PathVariable ("id") Long id) {
        return meetingRepository.findMeetingsOverDays(id);
    }

    @GetMapping("/meetings/count_work_packages/{id}")
    public @ResponseBody List <Map<String, Object>> getCountWorkPackagesByProjectId(@PathVariable ("id") Long id) {
        return meetingRepository.findCountWorkPackagesByProjectId(id);
    }
}
