package com.projects.crm.exceptions;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException(String args){
        super(args);
    }   
    public UnauthorizedAccessException(){
        super();
    } 
}
