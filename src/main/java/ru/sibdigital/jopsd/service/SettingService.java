package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.model.opsd.Setting;

public interface SettingService {
    String getOpsdHref();
    Setting findByName(String name);
}
