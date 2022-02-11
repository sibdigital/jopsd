package ru.sibdigital.jopsd.schedule.bot.tasks;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sibdigital.jopsd.service.SettingService;
import ru.sibdigital.jopsd.service.bot.BotService;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Component
public class CheckRegTargetSystemUser implements Runnable {

    private final static Logger botLogger = LoggerFactory.getLogger("botLoggerConnectException");
    private final static Logger botLoggerConnectException = LoggerFactory.getLogger("botLoggerConnectException");
    @Autowired
    private BotService botService;
    @Autowired
    private SettingService settingService;

    @Override
    public void run() {

        try {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(settingService.getPingBrbo()).openConnection();
                if (connection.getResponseCode() == 200) {
                    botService.checkRegTargetSystemUser();
                }
            } catch (ConnectException e) {
                botLoggerConnectException.error("Connect unavailable", e);
            }
        } catch (Exception e) {
            botLogger.error("ERROR at CheckRegTargetSystemUser: ", e);
        }
    }
}
