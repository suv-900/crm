package com.projects.crm.exceptions;

public class AdminNotFoundException extends RuntimeException{
   public AdminNotFoundException(String arg){
    super(arg);
   }
   public AdminNotFoundException(){
      super();
   } 
}
