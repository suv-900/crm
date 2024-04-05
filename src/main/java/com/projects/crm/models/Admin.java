package com.projects.crm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name="admins")
@Table
@Getter
@Setter
public class Admin {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int adminID;

    @Column(unique=true)
    private String username;

    private String password;
    
    private String email; 
}
