package ru.sibdigital.jopsd.schedule.bot;


import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import ru.sibdigital.jopsd.schedule.bot.tasks.*;

@Component
@Slf4j
public class SchedulerBrbo {
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    SchedulerBrboConfig schedulerBrboConfig;
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
    @Autowired
    private CheckRegTargetSystemUser checkRegTargetSystemUser;

    @PostConstruct
    public void scheduleRunnableWithCronTrigger() {

        taskScheduler.schedule(projectRegistryListener, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(projectRegistryElementListener, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(meetingsVia14Day, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(statusesMeeting, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(meetingsOverdue, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(projectMembers, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(meetingsMemberOverdue, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(memberMeetingsOverdue, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(findProject, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(foundProjects, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(findMember, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(foundMember, schedulerBrboConfig.cronTrigger());
        taskScheduler.schedule(checkRegTargetSystemUser, schedulerBrboConfig.cronTriggerCheckRegTargetSystemUser());
    }

}
