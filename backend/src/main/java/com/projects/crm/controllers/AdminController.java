package com.projects.crm.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.projects.crm.exceptions.AdminExistsException;
import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.exceptions.PostExistsException;
import com.projects.crm.exceptions.UnauthorizedAccessException;
import com.projects.crm.models.dto.AdminDTO;
import com.projects.crm.models.dto.AdminLogin;
import com.projects.crm.models.entitites.Admin;
import com.projects.crm.models.entitites.Post;
import com.projects.crm.services.AdminService;
import com.projects.crm.services.PostService;
import com.projects.crm.services.TokenService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private PostService postService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void registerAdmin(@Valid @RequestBody Admin admin,HttpServletResponse response)
    throws JWTCreationException,AdminExistsException,Exception 
    {
        if(adminService.adminExists(admin.getName(),admin.getEmail())){
            throw new AdminExistsException();
        }

        Long adminID = adminService.addAdmin(admin);
        
        String token = tokenService.generateToken(adminID);
        response.addHeader("Token",token);

    }
    
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void loginAdmin(@Valid @RequestBody AdminLogin admin,HttpServletResponse response)
    throws AdminNotFoundException,UnauthorizedAccessException,Exception 
    {
       Long adminID = adminService.loginAdmin(admin.getName(),admin.getPassword());

       String token = tokenService.generateToken(adminID);
       response.addHeader("Token",token);
    }

    @GetMapping("/allAdmins")
    @ResponseStatus(HttpStatus.OK)
    public List<AdminDTO> getAllAdmins(@RequestHeader(value = "Token",required=true)String token)
    throws JWTVerificationException,TokenExpiredException,Exception
    {
       tokenService.verifyToken(token);
       
       return adminService.getAllAdmins();
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdmin(@Valid @RequestBody Admin admin,@RequestHeader(value="Token",required=true)String token)
    throws JWTVerificationException,TokenExpiredException,Exception
    {
        tokenService.verifyToken(token);
        adminService.deleteAdmin(admin);     
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public void updateAdmin(@RequestBody Admin admin,@RequestHeader(value = "Token",required=true)String token)
    throws JWTVerificationException,TokenExpiredException,Exception
    {
        String s = tokenService.extractID(token);
        Long adminID = Long.parseLong(s); 
        admin.setId(adminID);

        adminService.updateAdmin(admin);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts/create")
    public void createPost(@Valid @RequestBody Post post,@RequestHeader(value="Token",required=true)String token)
    throws JWTVerificationException,TokenExpiredException,Exception
    {
        tokenService.verifyToken(token);
        
        if(postService.postExistsByTitle(post.getTitle())){
            throw new PostExistsException("post exists with title: "+post.getTitle());
        }
        postService.addPost(post);
    } 
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/posts/update/{id}")
    public Post updatePost(@RequestHeader(value="Token",required=true)String token,@RequestBody Post post,@RequestParam("id")Long postID)
    throws JWTVerificationException,TokenExpiredException,Exception
    {
        tokenService.verifyToken(token);
        post.setId(postID);
        return postService.updatePost(post);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/posts/delete/{id}")
    public void deletePost(@RequestHeader(value="Token",required=true)String token,@RequestParam("postID")Long postID)
    throws JWTVerificationException,TokenExpiredException,Exception
    {
        tokenService.verifyToken(token);
        postService.deletePostByID(postID);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/posts/getAllPosts")
    public List<Post> getAllPosts(@RequestHeader(value="Token",required=true)String token)
        throws JWTVerificationException,TokenExpiredException,Exception
    {
        tokenService.verifyToken(token);
        return postService.getAllPosts();
    }
}
