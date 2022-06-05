package com.example.webapp.controller;

import com.example.webapp.exception.ContactNotFoundException;
import com.example.webapp.model.Account;
import com.example.webapp.model.dto.AccountDto;
import com.example.webapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public String index(){
        return "account";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/{id}")
    public String editAccount(@PathVariable("id") Long id, Model model){
        AccountDto dto = accountService.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
        model.addAttribute("accountDto", dto);

        return "edit-account";
    }

    @PostMapping("/{id}")
    public String updateAccount(){
        return "edit-account";
    }

    @GetMapping("/new")
    public String newContact(AccountDto accountDto) {
        return "edit-account";
    }

    @PostMapping("/new")
    public String createAccount(@Valid AccountDto accountDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "edit-account";
        }
        accountService.save(accountDto);
        return "redirect:/login";
    }

}
