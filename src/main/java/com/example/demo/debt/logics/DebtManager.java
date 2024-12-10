package com.example.demo.debt.logics;


import com.example.demo.debt.Entity.Debt;
import com.example.demo.debt.Service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.demo.CommonTools.haveError;


@Service
public class DebtManager {

    private DebtService debtService;
    private DebtValidator validator;
    
    @Autowired
    public DebtManager(DebtService debtService, DebtValidator validator) {
        this.debtService = debtService;        
        this.validator = validator;
    }
    
    public RedirectAttributes addDebt(Debt debt, RedirectAttributes redirectAttributes) {
        redirectAttributes = validator.validateDebt(debt, redirectAttributes);         
        
        if (haveError(redirectAttributes))
            return redirectAttributes;   
        
        debt.setDebtName(debt.getDebtName().trim());
        debt.setCreditor(debt.getCreditor().trim());
        debtService.saveDebt(debt);
        redirectAttributes.addFlashAttribute("success", "Debt has been added successfully.");
        return redirectAttributes;
    }
}
