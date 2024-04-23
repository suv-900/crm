package com.projects.crm.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.entitites.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long>{
    
    @Modifying
    @Query(value="DELETE FROM posts WHERE id = :postID",nativeQuery=true)
    public void deletePostById(@Param("postID")Long postID);
}
