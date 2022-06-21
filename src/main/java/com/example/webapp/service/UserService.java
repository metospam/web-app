package com.example.webapp.service;

import com.example.webapp.model.Book;
import com.example.webapp.model.MyUser;
import com.example.webapp.model.User;
import com.example.webapp.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<UserDto> findById(Long id);

    List<UserDto> findAll();

    Long save(UserDto dto);

    void addBook(User user, Book book);

    void removeBook(User user, Book book);
}

