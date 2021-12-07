package ru.sibdigital.jopsd.schedule.bot.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sibdigital.jopsd.dto.bot.Request;
import ru.sibdigital.jopsd.service.SettingService;
import ru.sibdigital.jopsd.service.bot.BotService;
import ru.sibdigital.jopsd.utils.RequestUtils;

@Slf4j
@Component
public class MemberMeetings implements Runnable {

    @Autowired
    private BotService botService;

    @Autowired
    private SettingService settingService;

    @Override
    public void run() {


        final Request request = Request.builder()
                .eventTypeCode(settingService.getEventMeetingsMemberElem())
                .targetSystemCode(settingService.getTargetSystemCodeBrbo()).build();
        final String jsonRequest = RequestUtils.toJSON(request);

        try {
              botService.processMeetingsMemberElem(settingService.getUrlRequestBrbo(), jsonRequest);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
