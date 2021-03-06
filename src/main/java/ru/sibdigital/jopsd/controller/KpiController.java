package ru.sibdigital.jopsd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("kpi/report")
    public @ResponseBody String report (@RequestParam(value = "userId", required = false) Long userId,
                                        @RequestParam(value = "projectId", required = false) Long projectId) {
        byte[] bytes = null;
        if (userId != null) {
            bytes = kpiReportService.exportKpiReport(userId);
        } else if (projectId != null) {
            bytes = kpiReportService.exportKpiReportProject(projectId);
        }
        return new String(bytes);
    }
}
