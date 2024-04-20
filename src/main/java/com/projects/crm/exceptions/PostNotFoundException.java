package com.projects.crm.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String args){
        super(args);
    }
    public PostNotFoundException(){
        super();
    }     
}
