package com.projects.crm.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.crm.dao.AdminDao;
import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.models.dto.AdminDTO;
import com.projects.crm.models.entitites.Admin;
import com.projects.crm.services.AdminService;

import lombok.NonNull;

@Service
public class AdminService {
    @Autowired
    private AdminDao admindao;

    @Autowired
    private PasswordHasher hasher;

    public Long addAdmin(Admin admin)throws Exception{
        String hashedPassword = hasher.hashPassword(admin.getPassword());
        admin.setPassword(hashedPassword);
        return admindao.saveAdmin(admin);
    }
    
    public Long loginAdmin(String name,String password)throws AdminNotFoundException,Exception{
        Optional<Object[]> result = admindao.loginAdmin(name);    
        if(result.isEmpty()){
            throw new AdminNotFoundException();
        }
        Object[] res = result.get();
        Long adminID = (Long)res[0];
        String dbPassword =(String)res[1];

        hasher.comparePassword(dbPassword,password);
        
        return adminID;
    }
    public void deleteAdmin( Admin admin){
        admindao.deleteAdmin(admin);
    }

    public List<AdminDTO> getAllAdmins(){
        List<AdminDTO> adminDTOs = new LinkedList<>();
        
        List<Admin> admins = admindao.getAllAdmins();

        admins.forEach((admin)->{
            AdminDTO dto = new AdminDTO();
            dto.setName(admin.getName());
            dto.setEmail(admin.getEmail());
            adminDTOs.add(dto);
        });
        
        return adminDTOs;
    }
   
    public AdminDTO getAdminById(Long adminID){
        Admin admin = admindao.getAdminByID(adminID);
        AdminDTO dto = new AdminDTO();
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        return dto;
    }

    public Admin updateAdmin(@NonNull Admin admin){
        return admindao.updateAdmin(admin);
    }

    public boolean adminExists(String name,String email){
        return admindao.adminExists(name,email);
    }

    public Long getAdminCount(){
        return admindao.getCount();
    }
}
