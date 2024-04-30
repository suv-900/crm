package com.projects.crm.models.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(unique=true, nullable = false)
    @NotBlank(message="admin name cannot be blank.")
    private String name;

    @NotBlank(message="admin password cannot be blank")
    @Size(min=5,max=10,message="password should be between 5 - 10 characters")
    @Column(nullable = false)
    private String password;
    
    @Email(message="email should be valid.")
    @Column(nullable = false)
    private String email;

    private Date createdat;
    
}
