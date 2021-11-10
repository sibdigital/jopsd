package ru.sibdigital.jopsd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sibdigital.jopsd.dto.elbudget.execution.Resultsexecution;
import ru.sibdigital.jopsd.model.opsd.Target;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TargetMatch {
    private Resultsexecution.RegProject.PurposeCriterias.PurposeCriteria purposeCriteria;
    private Target target;
}
