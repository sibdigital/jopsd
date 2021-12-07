package ru.sibdigital.jopsd.schedule.bot.tasks;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sibdigital.jopsd.dto.bot.Request;
import ru.sibdigital.jopsd.service.SettingService;
import ru.sibdigital.jopsd.service.bot.BotService;
import ru.sibdigital.jopsd.utils.RequestUtils;

@Slf4j
@Component
public class MembersProject implements Runnable {

    @Autowired
    private BotService botService;

    @Autowired
    private SettingService settingService;

    private final static Logger botLogger = LoggerFactory.getLogger("botLogger");

    @Override
    public void run() {

        try {
            final Request request = Request.builder()
                    .eventTypeCode(settingService.getEventMembersProject())
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo()).build();
            final String jsonRequest = RequestUtils.toJSON(request);

            botService.processMembersProject(settingService.getUrlRequestBrbo(), jsonRequest);

        } catch (Exception e) {
            botLogger.error("ERROR at MembersProject: ", e);
        }
    }

}
