package com.projects.crm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projects.crm.models.Post;
import com.projects.crm.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/post")
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public String getPostByID(@RequestParam("id")int postID,Model model){
        try{
            Post post=postService.getPost(postID);
            model.addAttribute("post",post);
            log.info("Post added: "+post.toString());
            return "viewPost.html";
        }catch(Exception e){
            model.addAttribute("error",e.getMessage());
            log.error("getPostByID: "+e.getMessage());
            return "error.html";
        }
        
    }
    
    @PostMapping("/create")
    public void createPost(@ModelAttribute("post")Post post,Model model,HttpServletResponse response){
        try{
            postService.addPost(post);
            response.setStatus(200);
        }catch(Exception e){
            log.error("createPost: "+e.getMessage());
            response.setStatus(500);
        }
    }
    
    @PostMapping("/update/{id}")
    public void updatePost(){

    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(){

    }
}
