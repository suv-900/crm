package com.projects.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.models.Admin;
import com.projects.crm.services.AdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/loginPage")
    public ModelAndView getLoginPage(){
        ModelAndView mv=new ModelAndView("loginPage.html","admin",new Admin());
        return mv;
    }

    @PostMapping("/loginAdmin")
    public String loginAdmin(@ModelAttribute("admin")Admin admin,Model model){
        log.info("Logging in admin"+admin.toString()); 
        try{
            if(!adminService.loginAdmin(admin)){
                model.addAttribute("error","Password doesnt match.");
                log.info("Password doesnt match"); 
                return "loginPage.html";
            }
            log.info("Login successful");
            return "viewPosts.html"; 
        }catch(AdminNotFoundException e){
            model.addAttribute("error","Admin doesnt exists.");
            log.info("Error: "+e.getMessage());
            return "loginPage.html";
        }catch(Exception e){
            model.addAttribute("error","Server Error");
            log.info("Error: "+e.getMessage());
            return "loginPage.html";
        }
    }
    
    @GetMapping("/registerPage")
    public ModelAndView getRegisterPage(){
        ModelAndView mv = new ModelAndView("registerPage.html","admin",new Admin());
        return mv;
    }

    @PostMapping("/registerAdmin")
    public String registerAdmin(@ModelAttribute("admin")Admin admin){
        adminService.addAdmin(admin);
        return "viewPosts.html"; 

    }
}
