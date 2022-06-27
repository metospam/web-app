package melaside.controller;

import melaside.model.User;
import melaside.model.dto.UserDto;
import melaside.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserService userService;

    @GetMapping("/{id}")
    public String editContact(@PathVariable("id") Long id, Model model)  {
        User user = userService.findById(id);
        model.addAttribute("id", user.getId());
        model.addAttribute("books", user.getBooks());
        model.addAttribute("username", user.getUsername());

        return "edit-user";
    }

    @PostMapping("/{id}")
    public String updateUser(){
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
