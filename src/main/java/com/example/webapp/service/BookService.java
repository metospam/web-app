package com.example.webapp.service;

import com.example.webapp.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<BookDto> findById(Long id);
    List<BookDto> findAll();
    Long save(BookDto dto);
}
