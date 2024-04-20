package com.projects.crm.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.projects.crm.exceptions.PostNotFoundException;
import com.projects.crm.models.Post;
import com.projects.crm.services.PostService;
import com.projects.crm.services.TokenService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private TokenService tokenService;

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
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void createPost(@Valid @RequestBody Post post,@RequestHeader(value="Token",required=true)String token)
    throws JWTVerificationException,TokenExpiredException,Exception
    {
        tokenService.verifyToken(token);
        
        postService.addPost(post);
    } 
    
    @PostMapping("/update/{id}")
    public Post updatePost(@RequestHeader(value="Token",required=true)String token,@RequestBody Post post,@RequestParam("id")Long postID)
    throws JWTVerificationException,TokenExpiredException,Exception
    {
        tokenService.verifyToken(token);
        post.setId(postID);
        return postService.updatePost(post);
    }
    
    @PostMapping("/delete/{id}")
    public void deletePost(@RequestHeader(value="Token",required=true)String token,@RequestParam("id")Long postID)
    throws JWTVerificationException,TokenExpiredException,Exception
    {
        tokenService.verifyToken(token);
        postService.deletePostById(postID);
    }

}
