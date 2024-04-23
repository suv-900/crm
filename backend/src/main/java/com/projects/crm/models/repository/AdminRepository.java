package com.projects.crm.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projects.crm.models.entitites.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{

    @Query(value = "SELECT password FROM users WHERE name = :username",nativeQuery =true)
    public String getPasswordByUsername(String password);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM users WHERE email = :email OR name = :username) AS user_exists",nativeQuery=true)
    public boolean adminExists(@Param("email")String email,@Param("username")String username);
}
