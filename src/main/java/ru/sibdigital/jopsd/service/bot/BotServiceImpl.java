package ru.sibdigital.jopsd.service.bot;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.bot.Button;
import ru.sibdigital.jopsd.dto.bot.ClsEventType;
import ru.sibdigital.jopsd.dto.bot.RegIncomRequest;
import ru.sibdigital.jopsd.dto.bot.RegSentMessage;
import ru.sibdigital.jopsd.model.opsd.Meeting;
import ru.sibdigital.jopsd.model.opsd.Project;
import ru.sibdigital.jopsd.model.opsd.User;
import ru.sibdigital.jopsd.service.SuperServiceImpl;
import ru.sibdigital.jopsd.utils.RequestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BotServiceImpl extends SuperServiceImpl implements BotService{

    private final static Logger botLogger = LoggerFactory.getLogger("botLogger");

    public String processProjectRegistry(String url, String json) {


        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir: incomRequests) {
            User user = userRepository.findByIdent(ir.getUserId());
            final List<Project> userProjects = projectRepo.findProjectsByUserRoles(user.getId());
            Map requestParentEvents = Map.of("event_types", ir.getEventTypeCode());
            List<ClsEventType> eventParentCode = RequestUtils. <String, Map<String, ClsEventType>>postEntities(settingService.getUrlEventParentBrbo(), requestParentEvents, ClsEventType.class);
            List<Button> buttonProjects = new ArrayList<>();
            for (ClsEventType parentCode: eventParentCode) {
                for (Project project : userProjects) {
                    Button button = new Button();
                    button.setEventTypeCode(parentCode.getCode());
                    button.setIdentificator(project.getId().toString());
                    button.setLabel(project.getName());
                    buttonProjects.add(button);
                }
            }
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventProjReestr())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .settings(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processProjectRegistryElement(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir: incomRequests) {
            Map requestParentEvents = Map.of("event_types", ir.getEventTypeCode());
            List<ClsEventType> eventParentCode = RequestUtils. <String, Map<String, ClsEventType>>postEntities(settingService.getUrlEventParentBrbo(), requestParentEvents, ClsEventType.class);

            List<Button> buttonProjects = new ArrayList<>();

            for (ClsEventType parentCode: eventParentCode) {

                    Button button = new Button();
                    button.setEventTypeCode(parentCode.getCode());
                    button.setIdentificator(ir.getRequestBody());
                    button.setLabel(parentCode.getName());
                    buttonProjects.add(button);
            }

            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventProjReestrElem())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .settings(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            final List<String> list = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processEventsVia14Days(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();


        for (RegIncomRequest ir: incomRequests) {
            List<Meeting> userMeetings = meetingRepository.findMeetingsOverDays(Long.valueOf(ir.getRequestBody()));
            List<Button> buttonProjects = new ArrayList<>();

            for (Meeting meeting: userMeetings) {

                Button button = new Button();
                button.setLabel(meeting.getTitle());
                buttonProjects.add(button);
            }
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventVia14Day())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processEventsStatus (String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir: incomRequests) {
            List<Map<String, Object>> statusesMeeting = statusRepository.findCountWorkPackagesByProjectId(Long.valueOf(ir.getRequestBody()));
            List<Button> buttonProjects = new ArrayList<>();

                for(Map<String, Object> status: statusesMeeting) {
                    Button button = new Button();
                    button.setLabel(status.get("name") + ":" + status.get("count"));
                    buttonProjects.add(button);
                }
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventStatuses())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processEventsOverdue (String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir: incomRequests) {
            List<Meeting> overdueMeetings = meetingRepository.findExpiredMeetingsByProjectId(Long.valueOf(ir.getRequestBody()));
            List<Button> buttonProjects = new ArrayList<>();

            for(Meeting meeting: overdueMeetings) {
                Button button = new Button();
                button.setLabel(meeting.getTitle());
                buttonProjects.add(button);
            }
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventOverdue())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processMembersProject (String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir: incomRequests) {
            List<User> membersProject = userRepository.findMembersByProjectId(Long.valueOf(ir.getRequestBody()));
            List<Button> buttonProjects = new ArrayList<>();
            Map requestParentEvents = Map.of("event_types", ir.getEventTypeCode());
            List<ClsEventType> eventParentCode = RequestUtils. <String, Map<String, ClsEventType>>postEntities(settingService.getUrlEventParentBrbo(), requestParentEvents, ClsEventType.class);

            for (ClsEventType parentCode: eventParentCode) {
                for (User member : membersProject) {
                    Button button = new Button();
                    button.setLabel(member.getLastname() + " " + member.getFirstname());
                    button.setIdentificator(String.valueOf(member.getId()));
                    button.setEventTypeCode(parentCode.getCode());
                    buttonProjects.add(button);
                }
            }
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventMembersProject())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .settings(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processMeetingsMemberElem(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir : incomRequests) {

            List<Button> buttonProjects = new ArrayList<>();
            Map requestParentEvents = Map.of("event_types", ir.getEventTypeCode());
            List<ClsEventType> eventParentCode = RequestUtils. <String, Map<String, ClsEventType>>postEntities(settingService.getUrlEventParentBrbo(), requestParentEvents, ClsEventType.class);

            for (ClsEventType parentCode: eventParentCode) {
                Button button = new Button();
                button.setLabel(parentCode.getName());
                button.setIdentificator(ir.getRequestBody());
                button.setEventTypeCode(parentCode.getCode());
                buttonProjects.add(button);
            }

            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventMeetingsMemberElem())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .settings(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processMeetingsMemberOverdue(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir : incomRequests) {
            List<Meeting> membersExpiredProject = meetingRepository.findExpiredMeetingsByUserId(Long.valueOf(ir.getRequestBody()));
            List<Button> buttonProjects = new ArrayList<>();

            for (Meeting meeting : membersExpiredProject) {
                Button button = new Button();
                button.setLabel(meeting.getTitle());
                button.setIdentificator(ir.getRequestBody());
                buttonProjects.add(button);
            }

            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventMeetingsMemberElemOverdue())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }
}
