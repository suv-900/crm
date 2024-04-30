package com.projects.crm.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandleErrors {
    public void print(Exception e){
        log.error(e.getStackTrace()[0].getFileName() +":"+ e.getStackTrace()[0].getLineNumber()+":"+e.getMessage());
        e.printStackTrace();
   } 
}
