package com.example.demo.account.DAO;


import com.example.demo.account.Entity.Account;

import java.util.List;


public interface AccountDAO {

    public List<Account> getAccounts();
    
    public Account getAccount(String theAccountNumber);
    
    public void saveAccount(Account theAccount);
    
    public void updateAccount(Account theAccount);
    
    public void removeAccount(String theAccountNumber);
    
    public List<String> getAllAccountNumbers();
    
}