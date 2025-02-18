package com.example.demo.budget.Service;


import com.example.demo.budget.DAO.BudgetDAO;
import com.example.demo.budget.Entity.Budget;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetDAO budgetDAO;

    public BudgetServiceImpl() {
        
    }

    @Override
    @Transactional
    public List<Budget> getBudgets() {
        return budgetDAO.getBudgets();
    }

    @Override
    @Transactional
    public Budget getBudgetById(int id) {
        return budgetDAO.getBudgetById(id);
    }

    @Override
    @Transactional
    public void addBudget(Budget budget) {
        budgetDAO.addBudget(budget);
    }

    @Override
    @Transactional
    public void removeBudget(int id) {
        budgetDAO.removeBudget(id);
    }

    @Override
    @Transactional
    public void updateBudget(Budget budget) {
        budgetDAO.updateBudget(budget);
    }
}
