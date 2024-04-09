package com.projects.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.crm.dao.PostDao;
import com.projects.crm.models.Post;

@Service
public class PostService {
    @Autowired
    private PostDao postDao;    

    public void addPost(Post post){
        this.postDao.addPost(post);
    }

    public Post getPost(int postID){
        Post post=this.postDao.getPost(postID);
        return post;
    }

    public List<Post> getAllPosts(){
        return this.postDao.getAllPosts();
    }

    public Post updatePost(Post post){
        Post p=postDao.updatePost(post);
        return p;
    }

    public void deletePost(Post post){
        postDao.deletePost(post);
    }

}
