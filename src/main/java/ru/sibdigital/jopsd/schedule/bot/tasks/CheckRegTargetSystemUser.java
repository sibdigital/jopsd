package ru.sibdigital.jopsd.schedule.bot.tasks;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sibdigital.jopsd.service.bot.BotService;

@Slf4j
@Component
public class CheckRegTargetSystemUser implements Runnable {

    @Autowired
    private BotService botService;

    private final static Logger botLogger = LoggerFactory.getLogger("botLogger");

    @Override
    public void run() {

        try {
              botService.checkRegTargetSystemUser();

        } catch (Exception e) {
            botLogger.error("ERROR at CheckRegTargetSystemUser: ", e);
        }
    }

}
