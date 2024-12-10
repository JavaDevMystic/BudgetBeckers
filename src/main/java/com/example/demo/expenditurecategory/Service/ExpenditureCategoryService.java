package com.example.demo.expenditurecategory.Service;


import com.example.demo.expenditurecategory.Entity.ExpenditureCategory;

import java.util.List;


public interface ExpenditureCategoryService {

    public List<ExpenditureCategory> getExpenditureCategories();
    
    public void saveExpenditureCategory(ExpenditureCategory expenditureCategory);
    
    public void deleteExpenditureCategory(String expenditureType);    
    
    public boolean isUnique(String expenditureType);
    
}
