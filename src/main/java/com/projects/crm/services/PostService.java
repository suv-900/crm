package com.projects.crm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.crm.models.Post;
import com.projects.crm.models.repository.PostRepository;
import com.projects.crm.services.PostService;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;    

    public void addPost(Post post){
        repository.save(post);
    }

    public Post getPost(long postID){
        return repository.getReferenceById(postID);
    }

    public List<Post> getAllPosts(){
        return repository.findAll();
    }

    public Optional<Post> getPostById(long postID){
        return repository.findById(postID);
    }

    public void deleteMultiple(Iterable<Long> idList)throws IllegalArgumentException,Exception{
        repository.deleteAllById(idList);
    }

    public Post updatePost(Post post){
        return repository.save(post);
    }

    public void deletePost(Post post){
        repository.delete(post);
    }

    public void deletePostById(Long postID){
        repository.deletePostById(postID);
    }
}
