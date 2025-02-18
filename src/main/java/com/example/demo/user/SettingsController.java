package com.example.demo.user;


import com.example.demo.user.Service.UserService;
import com.example.demo.user.entity.User;
import com.example.demo.user.logics.RegistrationManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/settings")
public class SettingsController {
    
    private UserService userService;
    private RegistrationManager registrationManager;
    private HttpServletRequest httpServletRequest;

    @Autowired
    public SettingsController(UserService userService, RegistrationManager registrationManager, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.registrationManager = registrationManager;
        this.httpServletRequest = httpServletRequest;
    }
    
    @PostMapping("/updateAccount")
    public String updateAccount(@ModelAttribute("user") User userForm, RedirectAttributes redirectAttributes) {
        redirectAttributes = registrationManager.updateAccount(userForm, redirectAttributes);
        return "redirect:/settings";
    }
    
    @PostMapping("/removeAccount")
    public String removeAccount(@ModelAttribute("user") User userForm, RedirectAttributes redirectAttributes) throws ServletException {
        redirectAttributes = registrationManager.removeAccount(userForm, redirectAttributes);
        httpServletRequest.logout();
        return "redirect:/loginPage";
    }   
}
