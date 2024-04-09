package com.projects.crm.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalControllerAdvice {
   
    @ExceptionHandler(Exception.class)   
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody 
    public String handleException(Exception e){
        return "ERROR";
    }
}
