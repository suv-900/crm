package com.projects.crm.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
public class Post {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)   
   private long id;

   @NotBlank(message="post title cannot be blank.")
   @Column(name="post_title",unique = true,nullable = false)
   private String postTitle;
   
   @NotBlank(message="post content cannot be blank.")
   @Column(name="post_content")
   private String postContent;

}