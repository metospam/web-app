package com.example.webapp.controller;

import com.example.webapp.model.dto.BookDto;
import com.example.webapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/store")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String index() {
        return "store";
    }



    @RequestMapping("/book/new")
    public String bookForm(@Valid BookDto dto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book-form";
        }
        long id = bookService.save(dto);
        return "redirect:/book/" + id;
    }
}
