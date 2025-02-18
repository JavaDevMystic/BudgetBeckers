package com.example.demo.account;


import com.example.demo.account.Entity.Account;
import com.example.demo.account.Service.AccountService;
import com.example.demo.account.logics.AccountManager;
import com.example.demo.currency.Service.CurrencyService;
import com.example.demo.transaction.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Currency;
import java.util.List;

import static com.example.demo.CommonTools.haveError;


@Controller
@RequestMapping("/accounts")
public class AccountsController {
    
    private AccountService accountService;
    private CurrencyService currencyService;
    private TransactionService transactionService;
    private AccountManager accountManager;

    @Autowired
    public AccountsController(AccountService accountService, CurrencyService currencyService, TransactionService transactionService, AccountManager accountManager) {
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.transactionService = transactionService;
        this.accountManager = accountManager;
    }
    
    @GetMapping("/delete")
    public String deleteAccount(@RequestParam("accountNumber") String accountNumber, RedirectAttributes redirectAttributes) {       
        accountManager.deleteAccount(accountNumber);        
        redirectAttributes.addAttribute("success", "delete");
        return "redirect:/accounts";
    }    
    
    @GetMapping("/newAccount")
    public String addNewAccountForm(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("currencies", currencyService.getCurrencies());        
        return "new-account";
    }
    
    @PostMapping("/newAccount/add")
    public String addNewAccount(@ModelAttribute("account") Account theAccount, RedirectAttributes redirectAttributes, Model model) {
        model = accountManager.addNewAccount(model, theAccount);
        if (haveError(model)) {
            return "new-account";           
        }
        redirectAttributes.addAttribute("success", "accountAdded");        
        return "redirect:/accounts";
    }
    
    @GetMapping("/edit")
    public String editAccountForm(@RequestParam("accountNumber") String accountNumber, Model model, RedirectAttributes redirectAttributes) {
        redirectAttributes = accountManager.checkAccountNumberValidity(accountNumber, redirectAttributes);
        if (haveError(redirectAttributes)) {
            return "redirect:/accounts";
        }
        Account account = accountService.getAccount(accountNumber);
        List<Currency> currencies = currencyService.getCurrencies();
        model.addAttribute("transactions", transactionService.getTransactions());
        model.addAttribute("account", account);
        model.addAttribute("currencies", currencies);        
        return "edit-account";
    }
    
    @PostMapping("/edit/applyChanges")
    public String editAccount(@ModelAttribute("account") Account theAccount, RedirectAttributes redirectAttributes, Model model) {        
        model = accountManager.editAccount(model, theAccount);
                
        if (haveError(model)) {
            model.addAttribute("currencies", currencyService.getCurrencies());             
            return "edit-account";
        }        
        redirectAttributes.addAttribute("success", "accountUpdate");        
        return "redirect:/accounts";
    }
}
