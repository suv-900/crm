package com.projects.crm.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.exceptions.PostNotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {
    
    @ExceptionHandler(Exception.class)   
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody 
    public String handleException(Exception e){
        log.error(e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler({AdminNotFoundException.class,PostNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String notFoundHandler(RuntimeException e){
        log.error(e.getMessage());
        return e.getMessage();
    }
}
