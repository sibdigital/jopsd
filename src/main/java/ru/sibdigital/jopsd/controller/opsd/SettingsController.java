package ru.sibdigital.jopsd.controller.opsd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.controller.SuperController;
import ru.sibdigital.jopsd.model.opsd.Setting;

@Controller
public class SettingsController extends SuperController {
    @GetMapping("/settings/findByName")
    public @ResponseBody
    Setting getSetting(@RequestParam("name") String name) {
        return settingService.findByName(name);
    }
}
