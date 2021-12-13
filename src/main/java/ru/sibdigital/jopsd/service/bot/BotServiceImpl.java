package ru.sibdigital.jopsd.service.bot;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.dto.bot.*;
import ru.sibdigital.jopsd.model.opsd.Project;
import ru.sibdigital.jopsd.model.opsd.User;
import ru.sibdigital.jopsd.model.opsd.WorkPackage;
import ru.sibdigital.jopsd.service.SuperServiceImpl;
import ru.sibdigital.jopsd.utils.BotUtils;
import ru.sibdigital.jopsd.utils.RequestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BotServiceImpl extends SuperServiceImpl implements BotService{

    private final static Logger botLogger = LoggerFactory.getLogger("botLogger");

    public String processProjectRegistry(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir : incomRequests) {
            User user = userRepository.findByIdent(ir.getUserId());
            final List<Project> userProjects = projectRepo.findProjectsByUserRoles(user.getId());
            Map requestParentEvents = Map.of("event_types", ir.getEventTypeCode(), "id_bot", ir.getIdBot(), "id_user", ir.getUserId());
            List<ClsEventType> eventParentCode = RequestUtils.<String, Map<String, ClsEventType>>postEntities(settingService.getUrlEventParentBrbo(), requestParentEvents, ClsEventType.class);
            List<Button> buttonProjects = new ArrayList<>();
            for (ClsEventType parentCode : eventParentCode) {
                for (Project project : userProjects) {
                    List<WorkPackage> totalWP = workPackageRepo.findAllByProjectIdAndStatuses(project.getId());
                    List<WorkPackage> overdueWP = workPackageRepo.findExpiredWorkPackagesByProjectId(project.getId());
                        double countTotalWp = totalWP.size();
                        double countOverdueWp = overdueWP.size();
                        double result = countOverdueWp / countTotalWp;

                        Button button = new Button();
                        button.setShareOverdue(result);
                        button.setEventTypeCode(parentCode.getCode());
                        button.setIdentificator(project.getId().toString());
                        button.setLabel(project.getName());
                        button.setIdBot(ir.getIdBot());
                        button.setIdProject(project.getId().toString());
                        buttonProjects.add(button);

                }

            }
            List<Object> messageList = BotUtils.createMessage(buttonProjects, ir.getCodeMessenger());
            RegSentMessage rsm = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventProjReestr())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .settings(RequestUtils.toJSON(messageList))
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processProjectRegistryElement(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir: incomRequests) {
            Map requestParentEvents = Map.of("event_types", ir.getEventTypeCode(), "id_bot", ir.getIdBot(), "id_user", ir.getUserId());
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

            List<Object> messageList = BotUtils.createMessage(buttonProjects, ir.getCodeMessenger());
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventProjReestrElem())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .settings(RequestUtils.toJSON(messageList))
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
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
            List<WorkPackage> userWorkPackages = workPackageRepo.findWorkPackagesOverDays(Long.valueOf(ir.getRequestBody()));
            List<Button> buttonProjects = new ArrayList<>();
            String hostName = settingService.getHostName();
            String check_mark = new String(Character.toChars(0x1F5F8));

            for (WorkPackage workPackage: userWorkPackages) {
                Button button = new Button();
                button.setLabel(workPackage.getSubject());
                button.setIdBot(ir.getIdBot());
                button.setLink(BotUtils.createLink(ir.getCodeMessenger(), hostName, workPackage.getId()));
                button.setEmoji(check_mark);
                button.setText(workPackage.getSubject());
                buttonProjects.add(button);
            }
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventVia14Day())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processEventsStatus (String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir: incomRequests) {
            List<Map<String, Object>> statusesMeeting = statusRepository.findCountWorkPackagesByProjectId(Long.valueOf(ir.getRequestBody()));
            List<Button> buttonProjects = new ArrayList<>();
            String check_mark = new String(Character.toChars(0x1F5F8));

                for(Map<String, Object> status: statusesMeeting) {
                    Button button = new Button();
                    button.setLabel(status.get("name") + ":" + status.get("count"));
                    button.setIdBot(ir.getIdBot());
                    button.setEmoji(check_mark);
                    buttonProjects.add(button);
                }
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventStatuses())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processEventsOverdue (String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();
        String hostName = settingService.getHostName();

        for (RegIncomRequest ir: incomRequests) {
            List<WorkPackage> overdueWorkPackages = workPackageRepo.findExpiredWorkPackagesByProjectId(Long.valueOf(ir.getRequestBody()));
            List<Button> buttonProjects = new ArrayList<>();
            String check_mark = new String(Character.toChars(0x1F5F8));

            for(WorkPackage workPackage: overdueWorkPackages) {
                Button button = new Button();
                button.setLabel(workPackage.getSubject());
                button.setLink(BotUtils.createLink(ir.getCodeMessenger(), hostName, workPackage.getId()));
                button.setEmoji(check_mark);
                button.setText(workPackage.getSubject());
                button.setIdBot(ir.getIdBot());
                buttonProjects.add(button);
            }
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventOverdue())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processMembersProject (String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir: incomRequests) {
            List<User> membersProject = userRepository.findMembersByProjectId(Long.valueOf(ir.getRequestBody()));
            List<Button> buttonProjects = new ArrayList<>();
            Map requestParentEvents = Map.of("event_types", ir.getEventTypeCode(), "id_bot", ir.getIdBot(), "id_user", ir.getUserId());
            List<ClsEventType> eventParentCode = RequestUtils. <String, Map<String, ClsEventType>>postEntities(settingService.getUrlEventParentBrbo(), requestParentEvents, ClsEventType.class);

            for (ClsEventType parentCode: eventParentCode) {
                for (User member : membersProject) {
                    List<WorkPackage> totalWp = workPackageRepo.findAllByUserIdIdAndStatuses(member.getId(), Long.valueOf(ir.getIdProject()));
                    List<WorkPackage> overdueWp = workPackageRepo.findExpiredWorkPackagesByUserIdAndProject(member.getId(), Long.valueOf(ir.getIdProject()));
                    double countTotalWp =  totalWp.size();
                    double countOverdueWp = overdueWp.size();
                    double result = countOverdueWp/countTotalWp;

                    String patronymic = member.getPatronymic()!= null && !member.getPatronymic().equals("") ? member.getPatronymic().trim() : " ";
                    String firstname = member.getFirstname()!= null && !member.getFirstname().equals("") ? member.getFirstname().trim() : " ";
                    String lastname = member.getLastname()!= null && !member.getLastname().equals("") ? member.getLastname().trim() : " ";

                    Button button = new Button();
                    button.setShareOverdue(result);
                    button.setLabel(lastname + " " + firstname.charAt(0) + "." + patronymic.charAt(0));
                    button.setIdentificator(String.valueOf(member.getId()));
                    button.setIdBot(ir.getIdBot());
                    button.setEventTypeCode(parentCode.getCode());
                    buttonProjects.add(button);
                }
            }
            List<List<Object>> messageListTelegram = BotUtils.createSplitKeyboardTelegram(buttonProjects, ir.getCodeMessenger());
            RegSentMessage rsm = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventMembersProject())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .settings(RequestUtils.toJSON(messageListTelegram))
                    .build();
            messages.add(rsm);

        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processMeetingsMemberElem(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir : incomRequests) {

            List<Button> buttonProjects = new ArrayList<>();
            Map requestParentEvents = Map.of("event_types", ir.getEventTypeCode(), "id_bot", ir.getIdBot(), "id_user", ir.getUserId());
            List<ClsEventType> eventParentCode = RequestUtils. <String, Map<String, ClsEventType>>postEntities(settingService.getUrlEventParentBrbo(), requestParentEvents, ClsEventType.class);

            for (ClsEventType parentCode: eventParentCode) {
                Button button = new Button();
                button.setLabel(parentCode.getName());
                button.setIdentificator(ir.getRequestBody());
                button.setIdBot(ir.getIdBot());
                button.setEventTypeCode(parentCode.getCode());
                buttonProjects.add(button);
            }
            List<Object> messageList = BotUtils.createMessage(buttonProjects, ir.getCodeMessenger());
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventMeetingsMemberElem())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .settings(RequestUtils.toJSON(messageList))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processMeetingsMemberOverdue(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();
        String hostName = settingService.getHostName();
        String check_mark = new String(Character.toChars(0x1F5F8));

        for (RegIncomRequest ir : incomRequests) {
            List<WorkPackage> membersExpiredProject = workPackageRepo.findExpiredWorkPackagesByUserIdAndProject(Long.valueOf(ir.getRequestBody()), Long.valueOf(ir.getIdProject()));
            List<Button> buttonProjects = new ArrayList<>();

            for (WorkPackage workPackage : membersExpiredProject) {
                Button button = new Button();
                button.setLabel(workPackage.getSubject());
                button.setIdBot(ir.getIdBot());
                button.setLink(BotUtils.createLink(ir.getCodeMessenger(), hostName, workPackage.getId()));
                button.setIdentificator(ir.getRequestBody());
                buttonProjects.add(button);
                button.setEmoji(check_mark);
            }
            RegSentMessage rsm  = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventMeetingsMemberElemOverdue())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .userId(ir.getUserId())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processFindProject(String url, String json) {

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
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
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
                List<WorkPackage> totalWP = workPackageRepo.findAllByProjectIdAndStatuses(project.getId());
                List<WorkPackage> overdueWP = workPackageRepo.findExpiredWorkPackagesByProjectId(project.getId());
                double countTotalWp = totalWP.size();
                double countOverdueWp = overdueWP.size();
                double result = countOverdueWp / countTotalWp;

                Button button = new Button();
                button.setEventTypeCode(eventType);
                button.setShareOverdue(result);
                button.setLabel(project.getName());
                button.setIdBot(ir.getIdBot());
                button.setIdProject(project.getId().toString());
                button.setIdentificator(project.getId().toString());
                buttonProjects.add(button);
            }

            List<Object> messageList = BotUtils.createMessage(buttonProjects, ir.getCodeMessenger());
            RegSentMessage rsm = RegSentMessage.builder()
                    .eventTypeCode(settingService.getEventProjMessage())
                    .idIncomRequest(ir.getIdIncomRequest())
                    .userId(ir.getUserId())
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .settings(RequestUtils.toJSON(messageList))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processFindMember(String url, String json) {

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
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .text(RequestUtils.toJSON(buttonProjects))
                    .build();
            messages.add(rsm);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String processFoundMember(String url, String json) {

        final List<RegIncomRequest> incomRequests = RequestUtils.<RegIncomRequest, String>postEntities(url, json, RegIncomRequest.class);
        final List<RegSentMessage> messages = new ArrayList<>();

        for (RegIncomRequest ir : incomRequests) {
            try {
                List<Button> buttonProjects = new ArrayList<>();
                Long idProject = Long.valueOf(ir.getIdProject());
                String fio = ir.getRequestBody();
                List<String> fioList = Pattern.compile(" ")
                        .splitAsStream(fio)
                        .collect(Collectors.toList());

                    String lastname = fioList.size() > 0 ? fioList.get(0) : "";
                    String firstname = fioList.size() > 1 ? fioList.get(1) : "";
                    String patronymic = fioList.size() > 2 ? fioList.get(2) : "";

                    List<User> members = userRepository.findMembersByProjectIdAndFio(idProject, lastname, firstname, patronymic);
                    String eventType = settingService.getEventMeetingsMemberElem();

                    for (User user : members) {
                        List<WorkPackage> totalWp = workPackageRepo.findAllByUserIdIdAndStatuses(user.getId(), Long.valueOf(ir.getIdProject()));
                        List<WorkPackage> overdueWp = workPackageRepo.findExpiredWorkPackagesByUserIdAndProject(user.getId(), Long.valueOf(ir.getIdProject()));
                        double countTotalWp =  totalWp.size();
                        double countOverdueWp = overdueWp.size();
                        double result = countOverdueWp/countTotalWp;

                        Button button = new Button();
                        button.setShareOverdue(result);
                        button.setLabel(user.getLastname() + " " + user.getFirstname().charAt(0) + "." + user.getPatronymic().charAt(0));
                        button.setIdBot(ir.getIdBot());
                        button.setIdentificator(String.valueOf(user.getId()));
                        button.setEventTypeCode(eventType);
                        buttonProjects.add(button);
                    }

                    List<Object> messageList = BotUtils.createMessage(buttonProjects, ir.getCodeMessenger());
                    RegSentMessage rsm = RegSentMessage.builder()
                            .eventTypeCode(settingService.getEventMembMessage())
                            .idIncomRequest(ir.getIdIncomRequest())
                            .userId(ir.getUserId())
                            .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                            .settings(RequestUtils.toJSON(messageList))
                            .build();
                    messages.add(rsm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("messages", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlMessageBrbo(), request, ArrayList.class);
        }
        return "";
    }

    public String checkRegTargetSystemUser() {

        final List<User> users = userRepository.findUsersWithIdentificator();
        final List<RegTargetSystemUser> messages = new ArrayList<>();
        for (User user : users) {
            RegTargetSystemUser regTargetSystemUser = RegTargetSystemUser.builder()
                    .login(user.getLogin())
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .patronymic(user.getPatronymic())
                    .email(user.getMail())
                    .identificator(user.getIdentificator())
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo())
                    .build();

            messages.add(regTargetSystemUser);
        }
        if (!messages.isEmpty()) {
            Map request = Map.of("users", messages);
            List<String> response = RequestUtils.<String, Map<String, RegSentMessage>>postEntities(settingService.getUrlCreateUserBrbo(), request, ArrayList.class);
        }
        return "";
    }

}
