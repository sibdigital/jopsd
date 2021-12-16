package ru.sibdigital.jopsd.utils;

import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sibdigital.jopsd.dto.bot.Button;
import ru.sibdigital.jopsd.dto.bot.KeyboardTelegram;
import ru.sibdigital.jopsd.dto.bot.KeyboardViber;

import java.util.ArrayList;
import java.util.List;

public class BotUtils {

    private final static Logger botLogger = LoggerFactory.getLogger("botLogger");

    public static <T> List<T> createMessage(List<Button> buttons, String messenger) {

        List<T> list = new ArrayList<>();
        try {
            buttons.forEach(value -> {
                if(messenger != null && messenger.equals("telegram")) {
                    String green_circle = new String(Character.toChars(0x1F7E2));
                    String orange_circle = new String(Character.toChars(0x1F7E0));
                    String red_circle = new String(Character.toChars(0x1f534));
                    KeyboardTelegram keyboardTelegram = new KeyboardTelegram();
                    if (value.getShareOverdue() != null && !value.getShareOverdue().isNaN()) {
                        if (value.getShareOverdue() == 0) {
                            keyboardTelegram.setText(green_circle + " " + value.getLabel());
                        } else if (value.getShareOverdue() <= 0.2) {
                            keyboardTelegram.setText(orange_circle + " " + value.getLabel());
                        } else {
                            keyboardTelegram.setText(red_circle + " " + value.getLabel());
                        }
                    } else {
                        keyboardTelegram.setText(value.getLabel());
                    }
                    String projectSpliter = !value.getIdProject().equals(":") ? "*" : ":";
                    keyboardTelegram.setCallback_data(value.getEventTypeCode() + projectSpliter + value.getIdentificator());
                    list.add((T) keyboardTelegram);
                }
                else if(messenger != null && messenger.equals("viber")){
                    String green = "#a9e78e";
                    String orange = "#eda75d";
                    String red = "#ff6666";
                    KeyboardViber keyboardViber = new KeyboardViber();
                    if (value.getShareOverdue() != null && !value.getShareOverdue().isNaN()) {
                        if (value.getShareOverdue() == 0) {
                            keyboardViber.setBgColor(green);
                        } else if (value.getShareOverdue() <= 0.2) {
                            keyboardViber.setBgColor(orange);
                        } else {
                            keyboardViber.setBgColor(red);
                        }
                    } else {
                        keyboardViber.setText(value.getLabel());
                    }
                    String projectSpliter = !value.getIdProject().equals(":") ? "*" : ":";
                    keyboardViber.setActionBody(value.getEventTypeCode() + projectSpliter + value.getIdentificator());
                    keyboardViber.setColumns(6);
                    keyboardViber.setRows(1);
                    keyboardViber.setText(value.getLabel());
                    keyboardViber.setActionType("reply");
                    list.add((T) keyboardViber);
                }
            });
        } catch (Exception e) {
            botLogger.error(e.getMessage(), e);
        }
        return list;
    }

    public static <T> List<List<T>> createSplitKeyboardTelegram(List<Button> buttons, String messenger) {
        List<List<T>> splitList = new ArrayList<>();
        try {
            List<T> list = createMessage(buttons, messenger);
            if (messenger != null && messenger.equals("telegram")) {
                splitList = ListUtils.partition(list, 2);
            }
            else if (messenger != null && messenger.equals("viber")){
                List<KeyboardViber> keyboardVibers = (List<KeyboardViber>) list;
                keyboardVibers.forEach(value->{
                    value.setColumns(2);
                });
                splitList = (List<List<T>>) list;
            }

        } catch (Exception e) {
            botLogger.error(e.getMessage(), e);
        }
        return splitList;
    }

    public static String createLink(String platform, String host, Long idWp) {
        String link = "";
        try {
            if (platform.equals("telegram")){
                link = "<a href = 'http://" + host + "/work_packages/" + idWp + "/'>" + idWp + "</a>";
            }
            else if (platform.equals("viber")){
                link = "http://" + host + "/work_packages/" + idWp + "/";
            }

        } catch (Exception e) {
            botLogger.error(e.getMessage(), e);
        }
        return link;
    }
}



