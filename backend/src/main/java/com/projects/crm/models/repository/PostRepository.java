package com.projects.crm.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{
}
