package com.projects.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Long,Admin>{
    
}
