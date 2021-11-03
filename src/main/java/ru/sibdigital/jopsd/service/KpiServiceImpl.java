package ru.sibdigital.jopsd.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
@Slf4j
public class KpiServiceImpl extends SuperServiceImpl implements KpiService {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Object execute(String queryString) {
        Query query = entityManager.createNativeQuery(queryString);
        return query.getResultList();
    }
}
