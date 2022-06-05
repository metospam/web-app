package com.example.webapp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountDto {

    @NotBlank
    private String username;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

}
