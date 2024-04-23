package com.projects.crm.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.models.dto.AdminDTO;
import com.projects.crm.models.entitites.Admin;
import com.projects.crm.models.repository.AdminRepository;
import com.projects.crm.services.AdminService;

import jakarta.transaction.Transactional;
import lombok.NonNull;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepository repository;   
    
    @Autowired
    private PasswordHasher hasher;

    public void addAdmin(Admin admin)throws Exception{
        String hashedPassword = hasher.hashPassword(admin.getPassword());
        admin.setPassword(hashedPassword);

        repository.save(admin);
    }
    public boolean loginAdmin(String username,String password)throws AdminNotFoundException,Exception{
        String dbPassword = repository.getPasswordByUsername(username);
        
        if(dbPassword == null){
            throw new AdminNotFoundException("admin not found.");
        }

        return hasher.comparePassword(dbPassword,password);
    }
    public void deleteAdmin(@NonNull Admin admin){
        repository.delete(admin);
    }

    public List<AdminDTO> getAllAdmins(){
        List<Admin> adminList = repository.findAll();
        List<AdminDTO> adminDTOList = new LinkedList<>();

        adminList.forEach((admin)->{
            AdminDTO dto = new AdminDTO();
            dto.setUsername(admin.getName());
            dto.setEmail(admin.getEmail());
            adminDTOList.add(dto);
        });

        return adminDTOList;
    }
    public AdminDTO getAdminByID(long adminID)throws AdminNotFoundException{
        Optional<Admin> adminEntity = repository.findById(adminID);

        if(adminEntity.isEmpty()){
            throw new AdminNotFoundException();
        }
        AdminDTO dto = new AdminDTO();
        Admin admin = adminEntity.get();
        dto.setUsername(admin.getName());
        dto.setEmail(admin.getEmail());

        return dto;
    }
    public Admin updateAdmin(@NonNull Admin admin){
        return repository.save(admin);
    }

    public boolean adminExists(String username,String email){
        return repository.adminExists(email,username);
    }

    public Long getAdminCount(){
        return repository.count();
    }
}
