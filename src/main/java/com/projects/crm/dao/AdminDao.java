package com.projects.crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.Admin;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AdminDao {
    @Autowired 
    private SessionFactory sessionFactory;   
    
    @Transactional
    public void saveAdmin(Admin admin){
        Session session=this.sessionFactory.getCurrentSession();
        session.persist("admins",admin);
        log.info("Admin saved: "+admin);
    }

    @Transactional
    public List<Admin> getAllAdmins(){
        Session session=this.sessionFactory.getCurrentSession();
        List<Admin> admins=session.createQuery("from admins", Admin.class).getResultList();
        return admins;
    }

    @Transactional
    public void deleteAdmin(Admin admin){
        Session session=this.sessionFactory.getCurrentSession();
        session.remove(admin);
        log.info("Admin was removed: "+admin);
    }

    public Admin updateAdmin(Admin admin){
        Session session=this.sessionFactory.getCurrentSession();
        Admin updatedAdmin=session.merge(admin);
        return updatedAdmin;
    }
}
