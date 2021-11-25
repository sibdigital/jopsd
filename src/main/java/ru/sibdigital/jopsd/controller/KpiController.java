package ru.sibdigital.jopsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sibdigital.jopsd.dto.KpiDto;

@Controller
public class KpiController extends SuperController{
    @PostMapping("kpi/execute")
    public @ResponseBody Object execute(@RequestBody KpiDto body) {
        return kpiService.execute(body);
    }

    @PostMapping("kpi/save")
    public @ResponseBody KpiDto saveKpi(@RequestBody KpiDto body) {
        return kpiService.save(body);
    }
}
