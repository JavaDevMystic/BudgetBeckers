package com.example.demo.expenditurecategory.Service;



import com.example.demo.expenditurecategory.DAO.ExpenditureCategoryDAO;
import com.example.demo.expenditurecategory.Entity.ExpenditureCategory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenditureCategoryServiceImpl implements ExpenditureCategoryService {

    @Autowired
    ExpenditureCategoryDAO expCatDAO;
    
    @Override
    @Transactional
    public List<ExpenditureCategory> getExpenditureCategories() {
        return expCatDAO.getExpenditureCategories();
    }

    @Override
    @Transactional
    public void saveExpenditureCategory(ExpenditureCategory expenditureCategory) {
        expCatDAO.saveExpenditureCategory(expenditureCategory);
    }

    @Override
    @Transactional
    public void deleteExpenditureCategory(String expenditureType) {
        expCatDAO.deleteExpenditureCategory(expenditureType);
    }

    @Override
    @Transactional
    public boolean isUnique(String expenditureType) {
        return expCatDAO.isUnique(expenditureType);
    }

}
