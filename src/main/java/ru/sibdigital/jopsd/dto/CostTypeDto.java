package ru.sibdigital.jopsd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sibdigital.jopsd.model.opsd.CostType;
import ru.sibdigital.jopsd.model.opsd.EbCostType;
import ru.sibdigital.jopsd.model.opsd.Rate;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CostTypeDto {
    private CostType costType;
    private List<Rate> rateList;
    private List<EbCostType> ebCostTypeList;
}
