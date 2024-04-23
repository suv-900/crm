package com.projects.crm.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projects.crm.exceptions.PostNotFoundException;
import com.projects.crm.models.entitites.Post;
import com.projects.crm.services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Post getPostByID(@RequestParam("id")Long postID)
    throws PostNotFoundException,Exception
    {
        Optional<Post> post = postService.getPostById(postID);
        if(post.isEmpty()){
            throw new PostNotFoundException();
        }
        return post.get(); 
    }
    
}
