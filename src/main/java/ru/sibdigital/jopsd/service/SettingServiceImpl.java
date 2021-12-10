package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.Setting;

import java.util.Optional;

@Service
@Slf4j
public class SettingServiceImpl extends SuperServiceImpl implements SettingService {
    @Override
    public String getOpsdHref() {
        String protocol = settingRepository.findByName("protocol")
                                            .map(Setting::getValue)
                                            .orElse("http");

        String host_name = settingRepository.findByName("host_name")
                .map(Setting::getValue).
                orElse("");
        return protocol + "://" + host_name;
    }
    @Override
    public String getBaseBrbo() {
        Optional<String> protocolBrbo = settingRepository.findByName("protocol_brbo")
                .map(Setting::getValue);

        Optional<String>  urlBrbo = settingRepository.findByName("url_brbo")
                .map(Setting::getValue);

        Optional<String>  uroPortBrbo = settingRepository.findByName("url_port_brbo")
                .map(Setting::getValue);

        Optional<String>  urlContextPathBrbo = settingRepository.findByName("url_context_path_brbo")
                .map(Setting::getValue);

        if(urlBrbo.isPresent() && urlContextPathBrbo.isPresent() && protocolBrbo.isPresent()){
            return protocolBrbo.get() + "://" + urlBrbo.get() + "/" + urlContextPathBrbo.get();
        }
        else{
            return "";
        }
    }
    @Override
    public String getUrlMessageBrbo() {

        String urlMessageBrbo = settingRepository.findByName("url_message_brbo")
                .map(Setting::getValue)
                .orElse("/message");

        return getBaseBrbo() + urlMessageBrbo;
    }
    @Override
    public String getUrlRequestBrbo() {

        String urlRequestBrbo = settingRepository.findByName("url_request_brbo")
                .map(Setting::getValue)
                .orElse("/request");

        return getBaseBrbo() + urlRequestBrbo;
    }
    @Override
    public String getUrlEventParentBrbo() {

        String urlEventParentBrbo = settingRepository.findByName("url_event_parent_brbo")
                .map(Setting::getValue)
                .orElse("/event_type/parentEvents");

        return getBaseBrbo() + urlEventParentBrbo;
    }
    @Override
    public String getUrlCreateUserBrbo() {

        String urlCreateUser = settingRepository.findByName("url_create_user_brbo")
                .map(Setting::getValue)
                .orElse("/url");

        return getBaseBrbo() + urlCreateUser;
    }

    @Override
    public String getTargetSystemCodeBrbo() {

        String targetSystemCode = settingRepository.findByName("target_system_code")
                .map(Setting::getValue)
                .orElse("ISUP");

        return targetSystemCode;
    }

    @Override
    public String getEventProjReestr() {

        String eventProjReestr = settingRepository.findByName("event_proj_reestr")
                .map(Setting::getValue)
                .orElse("PROJ_REESTR");

        return eventProjReestr;
    }

    @Override
    public String getEventProjReestrElem() {

        String eventProjReestrElem = settingRepository.findByName("event_proj_reestr_elem")
                .map(Setting::getValue)
                .orElse("PROJ_REESTR_ELEM");

        return eventProjReestrElem;
    }

    @Override
    public String getEventVia14Day() {

        String eventVia14Day = settingRepository.findByName("event_via_14_day")
                .map(Setting::getValue)
                .orElse("WP_VIA_14_DAY");

        return eventVia14Day;
    }

    @Override
    public String getEventStatuses() {

        String eventStatuses = settingRepository.findByName("event_wp_status")
                .map(Setting::getValue)
                .orElse("WP_STATUS");

        return eventStatuses;
    }

    @Override
    public String getEventOverdue() {

        String eventOverdue = settingRepository.findByName("event_wp_overdue")
                .map(Setting::getValue)
                .orElse("WP_OVERDUE");

        return eventOverdue;
    }

    @Override
    public String getEventMembersProject() {

        String projectMembers = settingRepository.findByName("event_proj_members")
                .map(Setting::getValue)
                .orElse("PROJ_MEMBERS");

        return projectMembers;
    }

    @Override
    public String getEventMeetingsMemberElem() {

        String meetingsMemberElem = settingRepository.findByName("event_proj_members_elem")
                .map(Setting::getValue)
                .orElse("PROJ_MEMBERS_ELEM");

        return meetingsMemberElem;
    }

     @Override
     public String getEventMeetingsMemberElemOverdue() {

        String meetingsMemberElemOverdue = settingRepository.findByName("event_meetings_overdue_memb")
                .map(Setting::getValue)
                .orElse("MEETINGS_OVERDUE_MEMB");

        return meetingsMemberElemOverdue;
    }
    @Override
    public String getEventFindProject() {

        String eventFindProject = settingRepository.findByName("event_find_proj")
                .map(Setting::getValue)
                .orElse("FIND_PROJ");

        return eventFindProject;
    }

    @Override
    public String getEventProjMessage() {

        String eventProjMessage = settingRepository.findByName("event_get_proj_message")
                .map(Setting::getValue)
                .orElse("GET_FOR_SEARCHING_PROJ");

        return eventProjMessage;
    }

    @Override
    public String getEventFindMember() {

        String eventFindMemb = settingRepository.findByName("event_find_memb")
                .map(Setting::getValue)
                .orElse("FIND_MEMBER");

        return eventFindMemb;
    }
    @Override
    public String getEventMembMessage() {

        String eventMembMessage = settingRepository.findByName("event_get_member_message")
                .map(Setting::getValue)
                .orElse("GET_FOR_SEARCHING_MEMB");

        return eventMembMessage;
    }
    @Override
    public String getSizeProjectsForReestr() {

        String sizeProjects = settingRepository.findByName("size_projects_for_event_proj_reestr")
                .map(Setting::getValue)
                .orElse("20");

        return sizeProjects;
    }
    @Override
    public String getHostName() {

        String name = settingRepository.findByName("host_name")
                .map(Setting::getValue)
                .orElse("188.72.76.241:3001");

        return name;
    }


    @Override
    public Setting findByName(String name) {
        return settingRepository.findByName(name).orElse(null);
    }
}
