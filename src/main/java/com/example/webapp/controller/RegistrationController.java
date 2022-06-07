package com.example.webapp.controller;

import com.example.webapp.model.dto.UserDto;
import com.example.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/reg")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String newContact(UserDto userDto) {
        return "reg";
    }

    @PostMapping
    public String createAccount(@Valid UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "reg";
        }
        userService.save(userDto);
        return "home";
    }

}
