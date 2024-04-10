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
@Table(name="admins")
@Getter
@Setter
public class Admin {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name", unique=true)
    private String name;

    @Column(name="password")
    private String password;
    
    @Column(name="email")
    private String email;
    
    public Admin(){}

    @Override
    public String toString(){
        return this.id+" "+this.name+" "+this.password+" "+this.email;
    }
}
