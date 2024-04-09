package com.projects.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Long,Post>{
    
}
