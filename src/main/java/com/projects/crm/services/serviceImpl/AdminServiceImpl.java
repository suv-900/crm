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
    
    public Admin addAdmin(Admin admin){
        return adminDao.saveAdmin(admin);        
    }
    public boolean loginAdmin(Admin admin)throws AdminNotFoundException{
        return adminDao.loginAdmin(admin);
    }
    public void deleteAdmin(Admin admin){
        adminDao.deleteAdmin(admin);
    }

    public List<Admin> getAllAdmins(){
        return adminDao.getAllAdmins();
    }
    public Admin getAdminByID(int adminID)throws AdminNotFoundException{
        return adminDao.getAdminByID(adminID);
    }
    public void updateAdmin(Admin admin){
        adminDao.updateAdmin(admin);
    }
}
