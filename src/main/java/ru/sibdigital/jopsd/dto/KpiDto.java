package ru.sibdigital.jopsd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sibdigital.jopsd.model.opsd.Kpi;
import ru.sibdigital.jopsd.model.opsd.KpiVariable;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class KpiDto {
    private Kpi kpi;
    private List<KpiVariable> kpiVariables;
}
