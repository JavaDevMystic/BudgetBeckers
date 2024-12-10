package com.example.demo.currency.Service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Currency;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private EntityManager entityManager;
    
    @Autowired
    public CurrencyServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }    
    
    @Override
    @Transactional
    public List<Currency> getCurrencies() {
        Session currentSession = entityManager.unwrap(Session.class);        
        Query<Currency> theQuery = currentSession.createQuery("from currency", Currency.class);        
        List<Currency> currencies = (List<Currency>) theQuery.getResultList();        
        return currencies;        
    }
}
