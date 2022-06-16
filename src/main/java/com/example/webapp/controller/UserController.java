package com.example.webapp.controller;

import com.example.webapp.exception.UserNotFoundException;
import com.example.webapp.model.User;
import com.example.webapp.model.dto.UserDto;
import com.example.webapp.repository.UserRepo;
import com.example.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepo userRepo;

    @GetMapping("/{id}")
    public String editContact(@PathVariable("id") Long id, Model model) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        model.addAttribute("user", user);
        if(user.getBooks() != null) {
            model.addAttribute("books", user.getBooks());
        }

        return "edit-user";
    }

    @PostMapping("/{id}")
    public String updateUser() {
        return "edit-user";
    }

    @GetMapping("/registration")
    public String newUser(UserDto userDto) {
        return "user-form";
    }

    @PostMapping("/registration")
    public String createAccount(@Valid UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user-form";
        }
        userService.save(userDto);
        return "redirect:/login";
    }

}
