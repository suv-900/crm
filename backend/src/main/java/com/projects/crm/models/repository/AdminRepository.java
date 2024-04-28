package com.projects.crm.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.entitites.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
}
