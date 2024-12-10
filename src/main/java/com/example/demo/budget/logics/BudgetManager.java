package com.example.demo.budget.logics;

import com.example.demo.budget.Entity.Budget;
import com.example.demo.budget.Entity.Expenditure;
import com.example.demo.budget.Service.BudgetService;
import com.example.demo.budget.Service.ExpenditureService;
import com.example.demo.currency.Exchange.ExchangeManager;
import com.example.demo.currency.Service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Currency;
import java.util.List;

import static com.example.demo.CommonTools.haveError;


@Service
public class BudgetManager {

    private BudgetService budgetService;
    private ExpenditureService expService;
    private BudgetValidator validator;   
    private CurrencyService currencyService;
    
    @Autowired
    public BudgetManager(BudgetService budgetService, ExpenditureService expService, BudgetValidator validator, CurrencyService currencyService) {
        this.budgetService = budgetService;
        this.expService = expService;
        this.validator = validator;
        this.currencyService = currencyService;
    }
    
    public RedirectAttributes deleteExpenditure(int expId, int budgetId, RedirectAttributes redirectAttributes) {        
        if (!validator.isExpenditureCorrect(expId, budgetId)) {
            redirectAttributes.addFlashAttribute("error", "Something went wrong");
            return redirectAttributes;
        }
        expService.removeExpenditure(expId);
        redirectAttributes.addFlashAttribute("success", "Expenditure has been removed.");
        return redirectAttributes;
    }
    
    public float getSumOfAllExpenditures(int budgetId) {
        List<Expenditure> expenditures = expService.getExpenditures(budgetId);
        float sum = 0;
        for (Expenditure item : expenditures)
            sum += item.getAmount();
        return sum;
    }


    public Currency getCurrencyOfBudget(int budgetId) {
        List<Currency> currencies = currencyService.getCurrencies();
        Currency currency = null;  // Currency ning boshlang'ich qiymati null
        for (Currency item : currencies) {
            if (item.getCurrencyCode().equals(budgetService.getBudgetById(budgetId).getCurrency())) {
                currency = item;
                break;  // Valyuta topilgandan so'ng siklni to'xtatish
            }
        }
        return currency;  // Agar topilmasa, null qaytariladi
    }

    
    public RedirectAttributes addExpenditure(Expenditure expenditure, RedirectAttributes redirectAttributes) {
        expenditure.setDescription(expenditure.getDescription().trim());                
        if (validator.isExpenditureDescriptionCorrect(expenditure.getDescription())) {
            expService.addExpenditure(expenditure);        
            redirectAttributes.addFlashAttribute("success", "New expenditure has been added successfully.");            
        } else {
            redirectAttributes.addFlashAttribute("error", "Enter the description correctly.");
        }
        return redirectAttributes;
    }
    
    public RedirectAttributes addBudget(Budget budget, RedirectAttributes redirectAttributes) {
        budget.setBudgetName(budget.getBudgetName().trim());
        budget.setPurpose(budget.getPurpose().trim());        
        redirectAttributes = validator.validateBudget(budget, redirectAttributes);
        
        if (!haveError(redirectAttributes)) {
            budgetService.addBudget(budget);
            redirectAttributes.addAttribute("success", "added");
        }
        return redirectAttributes;
    }
    
    public void updateBudget(Budget budget) {
        List<Expenditure> expenditures = expService.getExpenditures(budget.getBudgetId());        
        ExchangeManager converter = new ExchangeManager();
        String currencyFrom =  budgetService.getBudgetById(budget.getBudgetId()).getCurrency();        
        String currencyTo = budget.getCurrency();
        budgetService.updateBudget(budget);    
        
        if (expenditures != null && expenditures.size() > 0) {
            for (Expenditure item : expenditures) {
                float amountFrom = item.getAmount();
                float temp = converter.convertCurrency(currencyTo, currencyFrom, amountFrom);
                item.setAmount(temp);
            }            
            expService.updateExpenditures(expenditures);
        }
    }    
}
