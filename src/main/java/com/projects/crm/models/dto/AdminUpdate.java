package com.projects.crm.models.dto;

import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUpdate {
   private Optional<String> email;
   private Optional<String> password; 
}
