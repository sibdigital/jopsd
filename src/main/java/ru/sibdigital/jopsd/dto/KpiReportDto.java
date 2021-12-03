package ru.sibdigital.jopsd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sibdigital.jopsd.model.opsd.Kpi;
import ru.sibdigital.jopsd.model.opsd.User;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class KpiReportDto {
    private Kpi kpi;
    private User user;
    private String value;
}
