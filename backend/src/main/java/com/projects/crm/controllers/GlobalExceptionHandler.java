package com.projects.crm.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.projects.crm.exceptions.AdminExistsException;
import com.projects.crm.exceptions.AdminNotFoundException;
import com.projects.crm.exceptions.PostNotFoundException;
import com.projects.crm.exceptions.UnauthorizedAccessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({AdminNotFoundException.class,
        PostNotFoundException.class})
    public void handleUserNotFoundException(AdminNotFoundException e){
    }
    

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void handleMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        log.error(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AdminExistsException.class)
    public String handleUserExistsException(AdminExistsException e){
        log.error(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class,
        NullPointerException.class,
        JWTCreationException.class
    })
    public void handleServerErrors(Exception e){
        log.error(e.getMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> sendValidationErrors(MethodArgumentNotValidException e){
        
        Map<String,String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        log.warn(errors.toString());
        return errors;
    }
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedAccessException.class,
        JWTVerificationException.class,
        MissingRequestHeaderException.class,
        TokenExpiredException.class})
    public String handleUnauthorizedUserException(UnauthorizedAccessException e){
        log.error(e.getMessage());
        return e.getMessage();
    }
}
