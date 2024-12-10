package com.example.demo.transaction;


import com.example.demo.account.Service.AccountService;
import com.example.demo.currency.Service.CurrencyService;
import com.example.demo.debt.Service.DebtService;
import com.example.demo.expenditurecategory.Service.ExpenditureCategoryService;
import com.example.demo.transaction.Entity.Transaction;
import com.example.demo.transaction.dto.TransactionSearch;
import com.example.demo.transaction.logics.TransactionsManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

import static com.example.demo.CommonTools.haveError;
import static com.example.demo.transaction.logics.TransactionsManager.isDebtRepayment;
import static com.example.demo.transaction.logics.TransactionsManager.isSearchTemplatePlanned;


@Controller
@RequestMapping("/transactions")
public class TransactionsController {
    
    private AccountService accountService;
    private DebtService debtService;
    private ExpenditureCategoryService expCatService;
    private CurrencyService currencyService;
    private TransactionsManager transactionsManager;

    @Autowired
    public TransactionsController(AccountService accountService, DebtService debtService, ExpenditureCategoryService expCatService, CurrencyService currencyService, TransactionsManager transactionsManager) {
        this.accountService = accountService;
        this.debtService = debtService;
        this.expCatService = expCatService;
        this.currencyService = currencyService;
        this.transactionsManager = transactionsManager;
    }    
    
    
    @GetMapping("/delete")
    public String deleteTransaction(@RequestParam("id") int id, @RequestParam("planned") String ifPlanned, RedirectAttributes redirectAttributes) {        
        redirectAttributes = transactionsManager.deleteTransaction(id, redirectAttributes);        
        if (ifPlanned.equals("true")) {
            return "redirect:/planned-transactions";        
        }
        return "redirect:/transaction-history";
    }
    
    @GetMapping("/newTransaction")
    public String addNewTransactionForm(@RequestParam("planned") String ifPlanned, Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("accounts", accountService.getAccounts());
        model.addAttribute("expenditureTypes", expCatService.getExpenditureCategories());
        model.addAttribute("currencies", currencyService.getCurrencies());    
        model.addAttribute("debts", debtService.getDebts());           
        if (ifPlanned.equals("true")) {
            return "new-planned-transaction";
        }
        return "new-transaction";
    }
    
    @GetMapping("/newDebtRepayment")
    public String addNewTransactionForm(@RequestParam("id") int debtId, Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("accounts", accountService.getAccounts());
        model.addAttribute("currencies", currencyService.getCurrencies());    
        model.addAttribute("debt", debtService.getDebtById(debtId));
        return "new-debt-repayment";
    }
        
    @PostMapping("/newTransaction/add")
    public String addNewTransaction(@ModelAttribute("transaction") Transaction transaction, RedirectAttributes redirectAttributes) {        
        boolean isTransactionPlanned = transactionsManager.isTransactionPlanned(transaction);
        redirectAttributes = transactionsManager.addNewTransaction(transaction, redirectAttributes);
        if (haveError(redirectAttributes)) {
            if (isDebtRepayment(transaction)) {
                return "redirect:/transactions/newDebtRepayment?id=" + transaction.getDebtId();
            }
            return "redirect:/transactions/newTransaction?planned=" + isTransactionPlanned;
        }
        if (isTransactionPlanned) {
            return "redirect:/planned-transactions";
        }
        return "redirect:/transaction-history";
    }   
    
    @PostMapping("/search")
    public String searchTransactions(@ModelAttribute("searchTemplate") TransactionSearch searchTemplate, Model model, RedirectAttributes redirectAttributes) {
        Map data = transactionsManager.searchTransactions(searchTemplate, redirectAttributes);
        boolean isPlanned = isSearchTemplatePlanned(searchTemplate);        
        
        redirectAttributes = (RedirectAttributes)data.get("redirectAttributes");
        if (haveError(redirectAttributes)) {
            if (isPlanned) {
                return "redirect:/planned-transactions";
            }
            return "redirect:/transaction-history";
        }
        
        List<Transaction> transactions = (List<Transaction>)data.get("transactions");
        model.addAttribute("accounts", accountService.getAccounts());
        model.addAttribute("currencies", currencyService.getCurrencies());
        model.addAttribute("searchTemplate", new TransactionSearch());        
        model.addAttribute("transactions", transactions);
        model.addAttribute("message", "Found something that match your criteria.");        
        
        if (isPlanned)
             return "planned-transactions";
        return "transaction-history";
    }
    
}
