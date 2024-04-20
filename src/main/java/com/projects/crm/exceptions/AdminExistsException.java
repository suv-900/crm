package com.projects.crm.exceptions;

public class AdminExistsException extends RuntimeException{
   public AdminExistsException(){
    super();
   }
   public AdminExistsException(String args){
    super(args);
   }  
}
