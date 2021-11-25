package ru.sibdigital.jopsd.service.bot;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.bot.*;
import ru.sibdigital.jopsd.model.opsd.*;
import ru.sibdigital.jopsd.service.SuperServiceImpl;
import ru.sibdigital.jopsd.utils.RequestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BotServiceImpl extends SuperServiceImpl implements BotService{


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
                    button.setIdBot(ir.getIdBot());
                    //button.setText("Информация по последним " + settingService.getSizeProjectsForReestr() + " проектам в которых есть изменения");
                    buttonProjects.add(button);
                }

            }
            Map text = Map.of("text", "Информация по последним " + settingService.getSizeProjectsForReestr() + " проектам в которых есть изменения");
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventProjReestr())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .text(RequestUtils.toJSON(text))
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
                    button.setIdBot(ir.getIdBot());
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
                button.setIdBot(ir.getIdBot());
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
                    button.setIdBot(ir.getIdBot());
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
                button.setIdBot(ir.getIdBot());
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
                    button.setIdBot(ir.getIdBot());
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
                button.setIdBot(ir.getIdBot());
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
                button.setIdBot(ir.getIdBot());
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

    public String processFindProj(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir : incomRequests) {
            List<Button> buttonProjects = new ArrayList<>();

            Button button = new Button();
            button.setLabel("Введите название проекта");
            button.setIdentificator(ir.getRequestBody());
            button.setIdBot(ir.getIdBot());
            button.setEventTypeCode(ir.getEventTypeCode());
            buttonProjects.add(button);

            RegSentMessage rsm = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventFindProject())
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

    public String processFoundProject(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir : incomRequests) {
            List<Button> buttonProjects = new ArrayList<>();
            User user = userRepository.findByIdent(ir.getUserId());
            List<Project> projects = projectRepo.findProjectsByName(ir.getRequestBody(), user.getId());
            String eventType = settingService.getEventProjReestrElem();
            for (Project project : projects) {

                Button button = new Button();
                button.setEventTypeCode(eventType);
                button.setLabel(project.getName());
                button.setIdBot(ir.getIdBot());
                button.setIdentificator(project.getId().toString());
                buttonProjects.add(button);
            }

            RegSentMessage rsm = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventProjMessage())
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

    public String processFindMemb(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir : incomRequests) {
            List<Button> buttonProjects = new ArrayList<>();

            Button button = new Button();
            button.setLabel("Введите ФИО участника");
            button.setIdentificator(ir.getRequestBody());
            button.setIdBot(ir.getIdBot());
            button.setEventTypeCode(ir.getEventTypeCode());
            buttonProjects.add(button);

            RegSentMessage rsm = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventFindProject())
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

    public String processFoundMemb(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir : incomRequests) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                RequestBody requestBody = objectMapper.readValue(ir.getRequestBody(), RequestBody.class);
                List<Button> buttonProjects = new ArrayList<>();
                Long idProject = Long.valueOf(requestBody.getIdProject());
                String fio = requestBody.getFio();
                List<String> fioList = Pattern.compile(" ")
                        .splitAsStream(fio)
                        .collect(Collectors.toList());

                    String lastname = fioList.size() > 0 ? fioList.get(0) : "#";
                    String firstname = fioList.size() > 1 ? fioList.get(1) : "#";
                    String patronymic = fioList.size() > 2 ? fioList.get(2) : "#";

                    //                String [] fioArr = fio.split(" ");
//                String firstname = "#";
//                String lastname = "#";
//                String patronymic = "#";
//                if(fioArr.length == 3){
//                     lastname = fioArr[0];
//                     firstname = fioArr[1];
//                     patronymic = fioArr[2];
//                }
//                else if(fioArr.length == 2) {
//                     lastname = fioArr[0];
//                     firstname = fioArr[1];
//                }
//                else if(fioArr.length == 1) {
//                     lastname = fioArr[0];
//                }

                    List<User> members = userRepository.findMembersByProjectIdAndFio(idProject, lastname, firstname, patronymic);
                    String eventType = settingService.getEventMeetingsMemberElem();

                    for (User user : members) {
                        Button button = new Button();
                        button.setLabel(user.getLastname() + " " + user.getFirstname());
                        button.setIdBot(ir.getIdBot());
                        button.setIdentificator(String.valueOf(user.getId()));
                        button.setEventTypeCode(eventType);
                        buttonProjects.add(button);
                    }

                    RegSentMessage rsm = RegSentMessage.builder()
                            .eventTypeCode(settingService.getEventMembMessage())
                            .idIncomRequest(ir.getIdIncomRequest())
                            .userId(ir.getUserId())
                            .settings(RequestUtils.toJSON(buttonProjects))
                            .build();
                    messages.add(rsm);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

}
