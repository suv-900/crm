package com.projects.crm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.crm.dao.AdminDao;
import com.projects.crm.models.Admin;

@Service
public class Adminservice {
    @Autowired
    private AdminDao adminDao;   
    
    public void addAdmin(Admin admin){
        adminDao.saveAdmin(admin);        
    }

    public void deleteAdmin(Admin admin){
        adminDao.deleteAdmin(admin);
    }

    public List<Admin> getAllAdmins(){
        return adminDao.getAllAdmins();
    }

    public void updateAdmin(Admin admin){
        adminDao.updateAdmin(admin);
    }
}
