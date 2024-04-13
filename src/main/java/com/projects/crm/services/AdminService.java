package com.projects.crm.services;

import java.util.List;

import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.models.Admin;

public interface AdminService {
    public Admin addAdmin(Admin admin);
    public void deleteAdmin(Admin admin);
    public boolean loginAdmin(Admin admin)throws AdminNotFoundException,Exception;
    public List<Admin> getAllAdmins();
    public void updateAdmin(Admin admin); 
    public Admin getAdminByID(int adminID)throws AdminNotFoundException;    
}
