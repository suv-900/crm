package com.projects.crm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer>{
}
