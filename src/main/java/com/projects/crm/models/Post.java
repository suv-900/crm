package com.projects.crm.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name="posts")
@Table(name="posts")
@Getter
@Setter
public class Post {

   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)   
   private int id;

   @Column(name="post_title")
   private String postTitle;
   
   @Column(name="post_description")
   private String postDescription;

   // @Column(name="post_authors")
   // private Admin author;

   @Override
   public String toString(){
      return this.id+" "+this.postTitle+" "+this.postDescription;
   }
}
