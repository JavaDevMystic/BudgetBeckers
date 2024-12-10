package com.example.demo.transaction.DAO;

import com.example.demo.security.SecurityService;
import com.example.demo.transaction.Entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

    private EntityManager entityManager;
    private SecurityService securityService;
    
    @Autowired
    public TransactionDAOImpl(EntityManager entityManager, SecurityService securityService) {
        this.entityManager = entityManager;
        this.securityService = securityService;
    }
    
    @Override
    public List<Transaction> getTransactions() {
        Session currentSession = entityManager.unwrap(Session.class);        
        String username = securityService.getUsernameFromSecurityContext();       
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());        
        Query<Transaction> query = currentSession.createQuery("from transactions where (dateTime<='" + time + "' and username='" + username + "') order by date(dateTime) DESC", Transaction.class);
        List<Transaction> tempList = (List<Transaction>) query.getResultList();        
        return tempList;        
    }

    @Override
    public List<Transaction> getPlannedTransactions() {
        Session currentSession = entityManager.unwrap(Session.class);        
        String username = securityService.getUsernameFromSecurityContext();       
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());        
        Query<Transaction> query = currentSession.createQuery("from transactions where (dateTime>'" + time + "' and username='" + username + "') order by date(dateTime) ASC", Transaction.class);
        List<Transaction> tempList = (List<Transaction>) query.getResultList();        
        return tempList;        
    }
    
    @Override
    public List<Transaction> getAllTransactions() {
        Session currentSession = entityManager.unwrap(Session.class);        
        String username = securityService.getUsernameFromSecurityContext();       
        Query<Transaction> query = currentSession.createQuery("from transactions where username='" + username + "' order by date(dateTime) DESC", Transaction.class);
        List<Transaction> tempList = (List<Transaction>) query.getResultList();
        return tempList;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        Session currentSession = entityManager.unwrap(Session.class);        
        String username = securityService.getUsernameFromSecurityContext();        
        transaction.setUsername(username);        
        currentSession.save(transaction);
    }

    @Override
    public void deleteTransaction(int transactionId) {
        Session currentSession = entityManager.unwrap(Session.class);        
        String username = securityService.getUsernameFromSecurityContext();        
        Query<Transaction> query = currentSession.createQuery("from transactions where (username='" + username + "' and transactionId='" + transactionId + "')", Transaction.class);
        Transaction transaction = query.getSingleResult();        
        currentSession.delete(transaction);
    }

    @Override
    public Transaction getTransactionById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);        
        String username = securityService.getUsernameFromSecurityContext();
        Query<Transaction> query = currentSession.createQuery("from transactions where (transactionId='" + id + "' and username='" + username + "')", Transaction.class);
        Transaction transaction;
        try {
            transaction = (Transaction) query.getSingleResult();
        }
        catch (NoResultException ex) {
            transaction = null;
        }
        
        return transaction; 
    }
    
    
    @Override 
    public void updateTransaction(Transaction transaction) {
        Session currentSession = entityManager.unwrap(Session.class); 
        currentSession.saveOrUpdate(transaction);
    }

}
