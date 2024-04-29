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

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Transactional(rollbackOn=Exception.class)
public class AdminDao {
    
    private SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
    
    public Long saveAdmin(Admin admin){
        Session session=this.sessionFactory.getCurrentSession();
        session.persist(admin);
        session.flush();
        return admin.getId();
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

    public boolean adminExists(String name,String email){
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            tx.setTimeout(10); 
            Integer count = session.createQuery("SELECT COUNT(*) FROM Admin WHERE name = :name OR email = :email",Integer.class)
            .setParameter("name",name)
            .setParameter("email",email)
            .uniqueResult();

            tx.commit();

            return count > 0;
        }catch(RuntimeException e){
            log.error("DAO: "+e.getMessage());
            if(tx != null) tx.rollback();
            throw e;
        }finally{
            session.close();
        }
    }
    public Long getCount(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Admin",Long.class).uniqueResult();
    }
}
