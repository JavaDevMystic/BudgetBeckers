package com.example.demo.debt.Service;


import com.example.demo.debt.Entity.Debt;

import java.util.List;

public interface DebtService {

    public List<Debt> getDebts();
    
    public List<Debt> getOnlyDebts();
    
    public List<Debt> getOnlyClaims();
    
    public Debt getDebt(String name);
    
    public int getDebtId(String name);
    
    public Debt getDebtById(int id);
    
    public void saveDebt(Debt theDebt);
    
    public void deleteDebt(int id);
    
    public boolean checkIfDebtExists(String debtName);
    
    public void updateDebt(Debt theDebt);
}
