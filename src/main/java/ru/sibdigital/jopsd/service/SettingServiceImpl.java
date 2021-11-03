package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.Setting;

@Service
@Slf4j
public class SettingServiceImpl extends SuperServiceImpl implements SettingService {
    @Override
    public String getOpsdHref() {
        String protocol = settingRepository.findByName("protocol")
                                            .map(Setting::getValue)
                                            .orElse("http");

        String host_name = settingRepository.findByName("host_name")
                .map(Setting::getValue)
                .orElse("");
        return protocol + "://" + host_name;
    }
    @Override
    public String getBaseBrbo() {
        String protocolBrbo = settingRepository.findByName("protocol_brbo")
                .map(Setting::getValue)
                .orElse("http");

        String urlBrbo = settingRepository.findByName("url_brbo")
                .map(Setting::getValue)
                .orElse("localhost");

        String uroPortBrbo = settingRepository.findByName("url_port_brbo")
                .map(Setting::getValue)
                .orElse("3000");

        String urlContextPathBrbo = settingRepository.findByName("url_context_path_brbo")
                .map(Setting::getValue)
                .orElse("api");

        return protocolBrbo + "://" + urlBrbo +":" + uroPortBrbo + "/" + urlContextPathBrbo ;
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
    public Setting findByName(String name) {
        return settingRepository.findByName(name).orElse(null);
    }
}
