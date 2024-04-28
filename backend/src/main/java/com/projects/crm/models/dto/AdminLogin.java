package com.projects.crm.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLogin {
    
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "password cannot be blank")
    private String password;
}
