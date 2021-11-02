package ru.sibdigital.jopsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KpiController extends SuperController{
    @PostMapping("kpi/execute")
    public @ResponseBody Object execute(@RequestBody String body) {
        return kpiService.execute(body);
    }
}
