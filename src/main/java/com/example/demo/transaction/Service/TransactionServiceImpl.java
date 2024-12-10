package com.example.demo.transaction.Service;

import com.example.demo.transaction.DAO.TransactionDAO;
import com.example.demo.transaction.Entity.Transaction;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    
    @Autowired
    private TransactionDAO transactionDAO;
    
    public TransactionServiceImpl() {
        
    }
    
    @Override
    @Transactional
    public List<Transaction> getTransactions() {
        return transactionDAO.getTransactions();
    }

    @Override
    @Transactional
    public List<Transaction> getPlannedTransactions() {
        return transactionDAO.getPlannedTransactions();
    }
    
    @Override
    @Transactional
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }
    
    @Override
    @Transactional
    public void saveTransaction(Transaction transaction) {
        transactionDAO.saveTransaction(transaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(int transactionId) {
        transactionDAO.deleteTransaction(transactionId);
    }

    @Override
    @Transactional
    public Transaction getTransactionById(int id) {
        return transactionDAO.getTransactionById(id);
    }

    @Override
    @Transactional
    public void updateTransaction(Transaction transaction) {
        transactionDAO.updateTransaction(transaction);
    }

}
