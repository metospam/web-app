package com.example.webapp.model.dto;

import com.example.webapp.validator.PasswordMatches;
import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Data
@PasswordMatches
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Transient
    @NotBlank
    private String matchingPassword;
}
