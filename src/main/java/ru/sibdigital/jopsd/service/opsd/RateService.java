package ru.sibdigital.jopsd.service.opsd;

import ru.sibdigital.jopsd.model.opsd.CostType;
import ru.sibdigital.jopsd.model.opsd.Rate;

import java.util.List;

public interface RateService {
    Rate save(Rate rate);
    void delete(Long rateId);
    void save(List<Rate> rateList, CostType costType);
}
