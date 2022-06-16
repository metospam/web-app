package com.example.webapp.controller;

import com.example.webapp.exception.BookNotFoundException;
import com.example.webapp.model.Book;
import com.example.webapp.model.MyUser;
import com.example.webapp.model.User;
import com.example.webapp.model.dto.BookDto;
import com.example.webapp.repository.BookRepo;
import com.example.webapp.repository.UserRepo;
import com.example.webapp.service.BookService;
import com.example.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final BookRepo bookRepo;

    private final UserService userService;

    private final UserRepo userRepo;

    @GetMapping
    public String index() {
        return "store";
    }

    @GetMapping("/new")
    public String newBook(BookDto bookDto){return "book-form";}

    @RequestMapping("/new")
    public String bookForm(@Valid BookDto bookDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book-form";
        }
        long id = bookService.save(bookDto);
        return "redirect:/book/" + id;
    }

    @PostMapping("/{id}")
    public String updateBook() {
        return "bookPage";
    }

    @GetMapping("/{id}")
    public String bookPage(
            @AuthenticationPrincipal MyUser myUser,
            @PathVariable("id") Long id, Model model){
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        model.addAttribute("book", book);
        model.addAttribute("user", myUser);

        return "bookPage";
    }

    @GetMapping("/subscribe/{id}")
    public String bookPage(
            @AuthenticationPrincipal MyUser myUser,
            @PathVariable("id") Long id){
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        if(userRepo.findById(myUser.getId()).stream().findFirst().isPresent()) {
            User user = userRepo.findById(myUser.getId()).stream().findFirst().get();
            userService.addBook(user, book);
        }
            return "redirect:/book/" + id;
    }

}
