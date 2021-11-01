package ru.sibdigital.jopsd.service.opsd;

import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.CostType;
import ru.sibdigital.jopsd.model.opsd.Rate;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl extends SuperServiceImpl implements RateService {

    @Override
    public Rate save(Rate rate) {
        rateRepository.save(rate);
        return rate;
    }

    @Override
    public void delete(Long rateId) {
        rateRepository.deleteById(rateId);
    }

    @Override
    public void save(List<Rate> rateList, CostType costType) {
        List<Rate> rates = rateList.stream()
                .filter(ctr -> (ctr.getId()!= null || ctr.getDeleted() == false))
                .collect(Collectors.toList());
        rates.forEach(ctr -> ctr.setCostType(costType));
        rateRepository.saveAll(rates);
    }
}
