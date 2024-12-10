package com.example.demo.spendingstructure;


import com.example.demo.account.Service.AccountService;
import com.example.demo.expenditurecategory.Service.ExpenditureCategoryService;
import com.example.demo.spendingstructure.dto.ChartTemplate;
import com.example.demo.spendingstructure.logics.SpendingStructureManager;
import com.example.demo.transaction.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

import static com.example.demo.CommonTools.haveError;


@Controller
@RequestMapping("/spending-structure")
public class SpendingStructureController {

    private TransactionService transactionService;
    private AccountService accountService;
    private ExpenditureCategoryService expService;
    private SpendingStructureManager spendingStructureManager;

    @Autowired
    public SpendingStructureController(TransactionService transactionService, AccountService accountService, ExpenditureCategoryService expService, SpendingStructureManager spendingStructureManager) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.expService = expService;
        this.spendingStructureManager = spendingStructureManager;
    }
    
    @PostMapping("/custom-chart")
    public String generateCustomChart(Model model, @ModelAttribute("chartTemplate") ChartTemplate template, RedirectAttributes redirectAttributes) {
        Map data = spendingStructureManager.getCustomChartData(model, redirectAttributes, template);        
        redirectAttributes = (RedirectAttributes)data.get("redirectAttributes");
        if (haveError(redirectAttributes)) {
            return "redirect:/spending-structure";
        }        
        model = (Model)data.get("model");
        return "custom-chart";
    }
}
