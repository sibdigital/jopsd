package ru.sibdigital.jopsd.service.opsd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sibdigital.jopsd.model.opsd.Lbo;
import ru.sibdigital.jopsd.service.SuperServiceImpl;

@Service
@Slf4j
public class LboServiceImpl extends SuperServiceImpl implements LboService {
    @Override
    public Lbo save(Lbo lbo) {
        lboRepository.save(lbo);
        return lbo;
    }

    @Override
    public void delete(Long lboId) {
        lboRepository.deleteById(lboId); ;
    }
}
