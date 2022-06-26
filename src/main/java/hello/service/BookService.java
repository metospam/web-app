package hello.service;

import hello.model.Book;
import hello.model.User;
import hello.model.dto.BookDto;

import java.util.List;

public interface BookService {
    Long save(BookDto dto);

    List<Book> search(String query);

    void delete(Book book);

    void addBookToUser(Book book, User user);

    void deleteBookFromUser(Book book);
}
