package com.projects.crm.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.projects.crm.config.HibernateConfig;
import com.projects.crm.models.entities.Post;

import jakarta.transaction.Transactional;

@Repository
@Transactional(rollbackOn=Exception.class)
public class PostDao {
   private SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

   public void addPost(Post post){
    Session session=sessionFactory.getCurrentSession();
    session.persist(post);
   }
   
   public boolean postExistsByTitle(String title){
      Session session = sessionFactory.getCurrentSession();
      Integer count = session.createQuery("select count(p) from Post p where p.title = :title",Integer.class)
         .setParameter("title",title)
         .uniqueResult();
      return count > 0;
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

   public List<Post> getFeedPosts(int offset)throws Exception{
      Session session = sessionFactory.getCurrentSession();
      Transaction tx = null;
      List<Post> posts = null;
      try{
         tx = session.beginTransaction();
         posts = session.createQuery("FROM Post",Post.class)
            .setFirstResult(offset)
            .setMaxResults(5)
            .list();
         tx.commit();
      }catch(RuntimeException e){
         if(tx != null)tx.rollback();
         throw e;
      }finally{
         session.close();
      }
      return posts;
   }
}
