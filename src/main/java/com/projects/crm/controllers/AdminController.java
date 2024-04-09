package com.projects.crm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.crm.models.Admin;

@RequestMapping("/admin")
@Controller
public class AdminController {
    
    @GetMapping("/registerPage")
    public String getRegisterPage(){
        return "registerPage";
    }

    @GetMapping("/loginPage")
    public String getLoginPage(){
        Admin admin=new Admin();
        return "loginPage";
    }

}
