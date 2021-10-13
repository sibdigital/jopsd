package ru.sibdigital.jopsd.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sibdigital.jopsd.controller.SuperController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;


// Светофор-2
@Controller
public class LightController extends SuperController {
    @RequestMapping(
            value = {"/generate_light_report/{format}/params"},
            method = RequestMethod.GET
    )
    public @ResponseBody
    String generateInspectionReport(@PathVariable String format,
                                    @RequestParam(value = "typeId") Long typeId,
                                    @RequestParam(value = "projectId", required = false) Long projectId,
                                    HttpServletResponse response) throws ParseException, IOException {

        byte[] bytes = lightService.exportLightReport(typeId, projectId);
        if (format.equals("pdf")) {
            response.setContentType("application/pdf");
        } else if (format.equals("html")) {
            response.setContentType("text/html");
        } else if (format.equals("xlsx")){
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=colorlight.xlsx");
        }

        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        out.close();
        return null;
    }
}
