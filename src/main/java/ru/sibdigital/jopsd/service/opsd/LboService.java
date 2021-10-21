package ru.sibdigital.jopsd.service.opsd;

import ru.sibdigital.jopsd.model.opsd.Lbo;

public interface LboService {
    Lbo save(Lbo lbo);

    void delete(Long lboId);

}
