package ru.sibdigital.jopsd.schedule;


import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import ru.sibdigital.jopsd.schedule.tasks.*;

@Component
@Slf4j
public class SchedulerBrbo {
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private CronTrigger cronTrigger;
    @Autowired
    private ProjectRegistryListener projectRegistryListener;
    @Autowired
    private ProjectRegistryElementListener projectRegistryElementListener;
    @Autowired
    private MeetingsVia14Day meetingsVia14Day;
    @Autowired
    private StatusesMeeting statusesMeeting;
    @Autowired
    private MeetingsOverdue meetingsOverdue;
    @Autowired
    private MembersProject projectMembers;
    @Autowired
    private MemberMeetings meetingsMemberOverdue;
    @Autowired
    private MemberMeetingsOverdue memberMeetingsOverdue;
    @Autowired
    private FindProject findProject;
    @Autowired
    private FoundProjects foundProjects;
    @Autowired
    private FindMember findMember;
    @Autowired
    private FoundMember foundMember;

    @PostConstruct
    public void scheduleRunnableWithCronTrigger() {

        taskScheduler.schedule(projectRegistryListener, cronTrigger);
        taskScheduler.schedule(projectRegistryElementListener, cronTrigger);
        taskScheduler.schedule(meetingsVia14Day, cronTrigger);
        taskScheduler.schedule(statusesMeeting, cronTrigger);
        taskScheduler.schedule(meetingsOverdue, cronTrigger);
        taskScheduler.schedule(projectMembers, cronTrigger);
        taskScheduler.schedule(meetingsMemberOverdue, cronTrigger);
        taskScheduler.schedule(memberMeetingsOverdue, cronTrigger);
        taskScheduler.schedule(findProject, cronTrigger);
        taskScheduler.schedule(foundProjects, cronTrigger);
        taskScheduler.schedule(findMember, cronTrigger);
        taskScheduler.schedule(foundMember, cronTrigger);
    }

}
