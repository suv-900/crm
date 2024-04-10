package com.projects.crm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.Post;

@Repository
public interface PostRepository extends CrudRepository<Post,Integer>{
    
}
