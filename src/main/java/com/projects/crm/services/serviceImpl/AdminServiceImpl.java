package com.projects.crm.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.crm.dao.AdminDao;
import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.models.Admin;
import com.projects.crm.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;   
    
    public void addAdmin(Admin admin){
        adminDao.saveAdmin(admin);        
    }
    public boolean loginAdmin(Admin admin)throws AdminNotFoundException,Exception{
        return adminDao.loginAdmin(admin);
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
