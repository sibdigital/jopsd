package ru.sibdigital.jopsd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;
import ru.sibdigital.jopsd.repository.opsd.WorkPackageRepo;

import java.util.List;

@Controller
public class MeetingController extends SuperController{

    @Autowired
    private WorkPackageRepo workPackageRepo;



    @GetMapping("/meetings/expired_meeting_users/{id}")
    public @ResponseBody List <WorkPackage> getExpiredMeetingsByUserId(@PathVariable ("id") Long id) {
        return workPackageRepo.findExpiredWorkPackagesByUserId(id);
    }

    @GetMapping("/meetings/expired_meeting_projects/{id}")
    public @ResponseBody List <WorkPackage> getExpiredMeetingsByProjectId(@PathVariable ("id") Long id) {
        return workPackageRepo.findExpiredWorkPackagesByProjectId(id);
    }

    @GetMapping("/meetings/meeting_over_days/{id}")
    public @ResponseBody List <WorkPackage> getMeetingsOverDaysByProjectId(@PathVariable ("id") Long id) {
        return workPackageRepo.findWorkPackagesOverDays(id);
    }
}
