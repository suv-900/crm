package com.projects.crm.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.projects.crm.config.HibernateConfig;
import com.projects.crm.models.entitites.Post;

import jakarta.transaction.Transactional;

@Repository
@Transactional(rollbackOn=Exception.class)
public class PostDao {
   private SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

   public void addPost(Post post){
    Session session=sessionFactory.getCurrentSession();
    session.persist(post);
   }
   
   public Post getPostByID(Long postID){
      Session session=sessionFactory.getCurrentSession();
      return session.get(Post.class,postID);
   }

   public void deletePostById(Long postID){
      Session session = sessionFactory.getCurrentSession();
      Post post = session.get(Post.class,postID);
      session.evict(post);
      session.remove(post);
   }
   
   public Post updatePost(Post post){
    Session session=sessionFactory.getCurrentSession();
    return session.merge(post);
   }

   public void deletePost(Post post){
    Session session=this.sessionFactory.getCurrentSession();
    session.remove(post);
   }
   
   public List<Post> getAllPosts(){
      Session session=sessionFactory.getCurrentSession();
      return session.createQuery("from Post",Post.class).list();
   }

   public List<Post> getPostsByIds(List<Long> ids){
      List<Post> postList = new LinkedList<>();
      Session session = sessionFactory.getCurrentSession();
      ids.forEach((id)->{
         Post post = session.get(Post.class,id);
         postList.add(post);
      });

      return postList;
   }

   public void deletePostByIds(List<Long> ids){
      Session session = sessionFactory.getCurrentSession();
      ids.forEach((id)->{
         Post post = session.get(Post.class,id);
         session.evict(post);
         session.remove(post);
      });

   }
}
