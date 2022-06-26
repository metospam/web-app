package com.example.webapp.service;

import com.example.webapp.model.Book;
import com.example.webapp.model.User;
import com.example.webapp.model.dto.BookDto;

import java.util.List;

public interface BookService {
    Long save(BookDto dto);

    List<Book> search(String query);

    void delete(Book book);

    void addBookToUser(Book book, User user);

    void deleteBookFromUser(Book book);
}
