package melaside.controller;

import melaside.exception.UserNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAdviceController {

    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFound(){

        return "userNotFound";
    }
}
