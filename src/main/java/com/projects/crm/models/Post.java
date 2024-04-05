package com.projects.crm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table
@Entity(name="posts")
public class Post {

   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)   
   private int postID;

   private String postTitle;
   
   private String postDescription;
}
