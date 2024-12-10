package com.example.demo.budget.DAO;


import com.example.demo.budget.Entity.Budget;

import java.util.List;


public interface BudgetDAO {

    public List<Budget> getBudgets();
    
    public Budget getBudgetById(int id);
    
    public void addBudget(Budget budget);
    
    public void removeBudget(int id);
    
    public void updateBudget(Budget budget);
        
}
