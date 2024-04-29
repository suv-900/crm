package com.projects.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.crm.dao.PostDao;
import com.projects.crm.exceptions.PostNotFoundException;
import com.projects.crm.models.entities.Post;
import com.projects.crm.services.PostService;

@Service
public class PostService {
    @Autowired
    private PostDao postdao;    

    public void addPost(Post post){
        postdao.addPost(post);
    }

    public Post getPostByID(Long postID)throws PostNotFoundException{
        Post post = postdao.getPostByID(postID);
        if(post == null){
            throw new PostNotFoundException();
        }
        return post;
    }
    public List<Post> getFeedPosts(int offset)throws RuntimeException,Exception{
        return postdao.getFeedPosts(offset);
    }
    public boolean postExistsByTitle(String title){
        return postdao.postExistsByTitle(title);
    }

    public Post updatePost(Post post)throws Exception{
        return postdao.updatePost(post);
    }

    public List<Post> getAllPosts()throws Exception{
        return postdao.getAllPosts();
    }
    public void deletePost(Post post)throws Exception{
        postdao.deletePost(post);
    }
    
    public void deletePostByID(Long postID)throws Exception{
        postdao.deletePostById(postID);
    }

    public List<Post> getPostsByIds(List<Long> ids)throws Exception{
        return postdao.getPostsByIds(ids);
    }

    public void deletePostByIds(List<Long> ids)throws Exception{
        postdao.deletePostByIds(ids);
    }
}
