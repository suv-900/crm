package com.projects.crm.models.entities;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Cacheable;
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
@Table
@Getter
@Setter
@Cache(region="posts",usage=CacheConcurrencyStrategy.READ_ONLY)
@Cacheable
public class Post {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)   
   private long id;

   @NotBlank(message="post title cannot be blank.")
   @Column(unique = true,nullable = false)
   private String title;
   
   @NotBlank(message="post content cannot be blank.")
   @Column(nullable = false)
   private String content;

}
