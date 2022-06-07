package com.example.webapp.controller;

import com.example.webapp.model.dto.UserDto;
import com.example.webapp.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;

    @GetMapping
    public String index(Model model){
        List<UserDto> list = userService.findAll();
        model.addAttribute("list", list);

        return "users";
    }

}
