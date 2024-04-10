package com.projects.crm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projects.crm.models.Admin;
import com.projects.crm.models.Post;
import com.projects.crm.services.AdminService;
import com.projects.crm.services.PostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private PostService postService;
    
    @GetMapping("/")
    public String sendHomePage(Model model){
        List<Admin> admins=adminService.getAllAdmins();
        List<Post> posts=postService.getAllPosts();
        System.out.println(admins+" "+posts);

        model.addAttribute("admins",admins);
        model.addAttribute("posts",posts);
        model.addAttribute("error-message",null);
        return "homePage.html";
    }

    @GetMapping("/styles.css")
    public String sendStyleSheet(){
        return "styles.css";
    }
}
