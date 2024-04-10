package com.projects.crm.services;

import java.util.List;

import com.projects.crm.models.Post;

public interface PostService {
    public void addPost(Post post);
    public Post getPost(int id);
    public List<Post> getAllPosts();
    public Post updatePost(Post post);
    public void deletePost(Post post);
}
