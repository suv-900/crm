package com.projects.crm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
   
    @GetMapping("/")
    public String sendHomePage(){
        ModelAndView modelView=new ModelAndView("templates/homePage");
        modelView.addObject("message","STAR");     
        return "homePage";
    }
}
