package com.projects.crm.exceptions;

public class PostExistsException extends RuntimeException{
   public PostExistsException(){
    super();
   }
   public PostExistsException(String args){
    super(args);
   } 
}
