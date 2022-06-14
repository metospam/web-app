package com.example.webapp.service.impl;

import com.example.webapp.model.Book;
import com.example.webapp.model.dto.BookDto;
import com.example.webapp.repository.BookRepo;
import com.example.webapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final ModelMapper modelMapper;

    @Override
    public Optional<BookDto> findById(Long id) {
        return bookRepo.findById(id).map(this::map);
    }

    @Override
    public List<BookDto> findAll() {
        return Streamable.of(bookRepo.findAll()).map(this::map).toList();
    }

    @Override
    @Transactional
    public Long save(BookDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());
        bookRepo.save(book);

        return book.getId();
    }

    public BookDto map(Book book){
        return modelMapper.map(book, BookDto.class);
    }

}
