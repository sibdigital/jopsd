package ru.sibdigital.jopsd.schedule.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sibdigital.jopsd.dto.bot.Request;
import ru.sibdigital.jopsd.service.SettingService;
import ru.sibdigital.jopsd.service.bot.BotService;
import ru.sibdigital.jopsd.utils.RequestUtils;

import java.util.Date;

@Slf4j
@Component
public class MembersProject implements Runnable {

    @Autowired
    private BotService botService;

    @Autowired
    private SettingService settingService;

    @Override
    public void run() {


        final Request request = Request.builder()
                .eventTypeCode(settingService.getEventMembersProject())
                .targetSystemCode(settingService.getTargetSystemCodeBrbo()).build();
        final String jsonRequest = RequestUtils.toJSON(request);

        try {
              botService.processMembersProject(settingService.getUrlRequestBrbo(), jsonRequest);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
