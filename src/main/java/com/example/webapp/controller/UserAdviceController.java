package com.example.webapp.controller;

import com.example.webapp.exception.UserNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAdviceController {

    @ExceptionHandler(UserNotFoundException.class)
    public String UserNotFound(UserNotFoundException e, Model model){
        model.addAttribute("id", e.getId());
        return "usernotfound";
    }
}
