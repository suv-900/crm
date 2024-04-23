package com.projects.crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.entitites.Post;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PostDao {
   @Autowired
   private SessionFactory sessionFactory;
   
   public void setSessionFactory(SessionFactory sf){
      this.sessionFactory=sf;
   }

   @Transactional
   public void addPost(Post post){
    Session session=this.sessionFactory.getCurrentSession();
    session.persist(post);
    log.info("Post added: "+post);
   }

   @Transactional
   public Post getPost(int postID){
    Session session=this.sessionFactory.getCurrentSession();
    Post post=session.find(Post.class,postID);
    return post;
   }

   @Transactional
   public List<Post> getAllPosts(){
    Session session=this.sessionFactory.getCurrentSession();
    List<Post> posts=session.createQuery("from posts",Post.class).getResultList();
    return posts;
   }

   @Transactional
   public Post updatePost(Post post){
    Session session=this.sessionFactory.getCurrentSession();
    Post p=session.merge(post);
    return p;
   }

   @Transactional 
   public void deletePost(Post post){
    Session session=this.sessionFactory.getCurrentSession();
    session.remove(post);
    log.info("Post deleted: "+post);
   }
}
