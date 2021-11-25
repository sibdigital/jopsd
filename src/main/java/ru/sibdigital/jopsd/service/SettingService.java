package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.model.opsd.Setting;

public interface SettingService {
    String getOpsdHref();
    String getBaseBrbo();
    String getUrlMessageBrbo();
    String getUrlRequestBrbo();
    String getUrlEventParentBrbo();
    String getTargetSystemCodeBrbo();
    String getEventProjReestr();
    String getEventProjReestrElem();
    String getEventVia14Day();
    String getEventStatuses();
    String getEventOverdue();
    String getEventMembersProject();
    String getEventMeetingsMemberElem();
    String getEventMeetingsMemberElemOverdue();
    String getEventFindProject();
    String getEventProjMessage();
    String getEventFindMember();
    String getEventMembMessage();
    String getSizeProjectsForReestr();

    Setting findByName(String name);
}
