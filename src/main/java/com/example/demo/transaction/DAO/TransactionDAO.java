package com.example.demo.transaction.DAO;


import com.example.demo.transaction.Entity.Transaction;

import java.util.List;

public interface TransactionDAO {
    
    public List<Transaction> getTransactions();
    
    public List<Transaction> getPlannedTransactions();
    
    public List<Transaction> getAllTransactions();
    
    public void saveTransaction(Transaction transaction);
    
    public void updateTransaction(Transaction transaction);
    
    public void deleteTransaction(int transactionId);
    
    public Transaction getTransactionById(int id);
}
