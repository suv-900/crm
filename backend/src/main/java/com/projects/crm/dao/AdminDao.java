package com.projects.crm.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.projects.crm.config.HibernateConfig;
import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.models.entities.Admin;
import com.projects.crm.utils.HandleErrors;

@Repository
public class AdminDao {
    private HandleErrors errors = new HandleErrors();    
    private SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
    
    public void saveAdmin(Admin admin){
        Session session=this.sessionFactory.getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();

            session.persist(admin);
            tx.commit();
        }catch(RuntimeException e){
            if(tx != null) tx.rollback();
            errors.print(e);
            throw e;
        }finally{
            session.close();
        }
    }

    public Long getIdentifier(Admin admin){
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        Long id = null;
        try{
            tx = session.beginTransaction();

            id = session.createQuery("select id from Admin where name = :name and email = :email",Long.class)
                .setParameter("name",admin.getName())
                .setParameter("email",admin.getEmail())
                .uniqueResult();

            tx.commit();
        }catch(RuntimeException e){
            if(tx != null) tx.rollback();
            errors.print(e);
            
            throw e;
        }finally{
            session.close();
        }
        return id;
    }

    public List<Admin> getAllAdmins(){
        Session session=this.sessionFactory.getCurrentSession();
        return session.createQuery("from Admin",Admin.class).list();
    }

    public Admin getAdminByID(Long adminID)throws AdminNotFoundException{
        Session session=this.sessionFactory.getCurrentSession();
        return session.get(Admin.class,adminID);
    }

    public void deleteAdmin(Admin admin){
        Session session=this.sessionFactory.getCurrentSession();
        session.evict(admin);
        session.remove(admin);
    }

    public Admin updateAdmin(Admin admin){
        Session session=this.sessionFactory.getCurrentSession();
        return session.merge(admin);
    }

    public Optional<Object[]> loginAdmin(String name)throws AdminNotFoundException{
        Session session = sessionFactory.getCurrentSession();
        
        Optional<Object[]> result = session.createQuery("select a.id,a.password from Admin where a.name = :name",Object[].class)
            .setParameter("name",name)
            .uniqueResultOptional();
        return result;
    }

    public boolean adminExists(String name,String email)throws Exception{
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        Long count = null;
        try{
            tx = session.beginTransaction();
            tx.setTimeout(10); 
            count = session.createQuery("SELECT COUNT(*) FROM Admin WHERE name = :name OR email = :email",Long.class)
            .setParameter("name",name)
            .setParameter("email",email)
            .uniqueResult();

            tx.commit();
        }catch(RuntimeException e){
            if(tx != null) tx.rollback();
            errors.print(e);
            throw e;
        }finally{
            session.close();
        }
        if(count == null) throw new Exception("Count=null is returned by the query");
        
        return count > 0;
    }
    public Long getCount(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Admin",Long.class).uniqueResult();
    }
}
