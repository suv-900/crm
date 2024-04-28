package com.projects.crm.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HibernateConfig {
   private static SessionFactory sessionFactory = buildSessionFactory();
   
   private static SessionFactory buildSessionFactory(){
        try{
            return new Configuration().configure().buildSessionFactory();
        }catch(Exception e){
            log.error("Couldnt build SessionFactory");
            throw e;
        }
   }

   public static SessionFactory getSessionFactory(){
    return sessionFactory;
   }
}
