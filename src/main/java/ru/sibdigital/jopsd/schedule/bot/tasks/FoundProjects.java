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
public class FoundProjects implements Runnable {

    private static final Logger botLogger = LoggerFactory.getLogger("botLogger");
    @Autowired
    private BotService botService;
    @Autowired
    private SettingService settingService;

    @Override
    public void run() {

        try {
            final Request request = Request.builder()
                    .eventTypeCode(settingService.getEventProjMessage())
                    .targetSystemCode(settingService.getTargetSystemCodeBrbo()).build();
            final String jsonRequest = RequestUtils.toJSON(request);
            botService.processFoundProject(settingService.getUrlRequestBrbo(), jsonRequest);
        } catch (Exception e) {
            botLogger.error("ERROR at FoundProject: ", e);
        }
    }
}
