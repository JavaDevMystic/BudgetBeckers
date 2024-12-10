package com.example.demo.debt;


import com.example.demo.currency.Service.CurrencyService;
import com.example.demo.debt.Entity.Debt;
import com.example.demo.debt.Service.DebtService;
import com.example.demo.debt.logics.DebtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.demo.CommonTools.haveError;


@Controller
@RequestMapping("/debts")
public class DebtController {

    private DebtService debtService;
    private CurrencyService currencyService;
    private DebtManager debtManager;
    
    @Autowired
    public DebtController(DebtService debtService, CurrencyService currencyService, DebtManager debtManager) {
        this.debtService = debtService;
        this.currencyService = currencyService;
        this.debtManager = debtManager;
    }
    
    @GetMapping("/delete")
    public String deleteDebt(@RequestParam("id") int id, RedirectAttributes redirectAttributes)
    {
        if (debtService.getDebtById(id) == null) {
            redirectAttributes.addFlashAttribute("error", "Something went wrong.");
            return "redirect:/debts";
        }        
        debtService.deleteDebt(id);        
        redirectAttributes.addFlashAttribute("success", "The debt has been removed successfully.");
        return "redirect:/debts";
    }
    
    @GetMapping("/new")
    public String newDebt(Model model) {        
        model.addAttribute("debt", new Debt());
        model.addAttribute("currencies", currencyService.getCurrencies());        
        return "new-debt";
    }
    
    @PostMapping("/new/add")
    public String addDebt(@ModelAttribute("debt") Debt debt, RedirectAttributes redirectAttributes) {
        redirectAttributes = debtManager.addDebt(debt, redirectAttributes);        
        if (haveError(redirectAttributes)) {
            return "redirect:/debts/new";        
        }            
        return "redirect:/debts";
    }
    
}
