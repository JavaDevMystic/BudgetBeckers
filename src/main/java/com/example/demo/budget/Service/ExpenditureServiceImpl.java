package com.example.demo.budget.Service;



import com.example.demo.budget.DAO.ExpenditureDAO;
import com.example.demo.budget.Entity.Expenditure;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenditureServiceImpl implements ExpenditureService {
    
    private ExpenditureDAO expenditureDAO;
  
    @Autowired
    public ExpenditureServiceImpl(ExpenditureDAO expenditureDAO) {
        this.expenditureDAO = expenditureDAO;
    }    
    
    @Override
    @Transactional
    public void addExpenditure(Expenditure expenditure) {
        expenditureDAO.addExpenditure(expenditure);
    }

    @Override
    @Transactional
    public void removeExpenditure(int id) {
        expenditureDAO.removeExpenditure(id);
    }

    @Override
    @Transactional
    public Expenditure getExpenditureById(int id) {
        return expenditureDAO.getExpenditureById(id);
    }

    @Override
    @Transactional
    public List<Expenditure> getExpenditures(int budgetId) {
        return expenditureDAO.getExpenditures(budgetId);
    }

    @Override
    @Transactional
    public void updateExpenditures(List<Expenditure> expenditures) {
        expenditureDAO.updateExpenditures(expenditures);
    }

}
