package ru.sibdigital.jopsd.service.bot;

import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.User;

@Service
public interface BotService {
    String processProjectRegistry(String url, String json);
    String processProjectRegistryElement(String url, String json);
    String processEventsVia14Days(String url, String json);
    String processEventsStatus (String url, String json);
    String processEventsOverdue (String url, String json);
    String processMembersProject (String url, String json);
    String processMeetingsMemberElem(String url, String json);
    String processMeetingsMemberOverdue(String url, String json);
    String processFindProj(String url, String json);
    String processFoundProject(String url, String json);
    String processFindMemb(String url, String json);
    String processFoundMemb(String url, String json);
    String checkRegTargetSystemUser();
}
