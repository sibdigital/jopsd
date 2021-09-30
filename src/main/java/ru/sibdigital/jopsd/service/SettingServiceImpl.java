package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.Setting;

@Service
@Slf4j
public class SettingServiceImpl extends SuperServiceImpl implements SettingService {
    @Override
    public String getOpsdHref() {
        String protocol = settingRepository.findByName("protocol")
                                            .map(Setting::getValue)
                                            .orElse("http");

        String host_name = settingRepository.findByName("host_name")
                .map(Setting::getValue)
                .orElse("");
        return protocol + "://" + host_name;
    }
}
