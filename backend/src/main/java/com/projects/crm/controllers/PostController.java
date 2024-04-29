package com.projects.crm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.exceptions.PostNotFoundException;
import com.projects.crm.models.entities.Post;
import com.projects.crm.services.PostService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Post getPostByID(@RequestParam("postID")Long postID)
    throws PostNotFoundException,Exception
    {
       return postService.getPostByID(postID); 
    }
    
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Post> getFeedPosts(){
        return postService.getFeedPosts();
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({AdminNotFoundException.class,
        PostNotFoundException.class})
    public void handleNotFoundException(AdminNotFoundException e){
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleGenericException(Exception e){
    }
}
