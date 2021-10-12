package ru.sibdigital.jopsd.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.controller.SuperController;

import java.text.ParseException;


// Светофор-2
@Controller
public class LightController extends SuperController {
    @RequestMapping(
            value = {"/generate_light_report"},
            method = RequestMethod.GET
    )
    public @ResponseBody
    String generateInspectionReport(@RequestParam(value = "typeId") Long typeId,
                                    @RequestParam(value = "projectId", required = false) Long projectId) throws ParseException {

        byte[] bytes = lightService.exportLightReport(typeId, projectId);
        String template = new String(bytes);
        return template;
    }
}
