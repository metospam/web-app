package com.example.webapp.model.dto;

import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Transient
    @NotBlank
    private String matchingPassword;
}
