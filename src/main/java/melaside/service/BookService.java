package melaside.service;

import melaside.model.Book;
import melaside.model.User;
import melaside.model.dto.BookDto;

import java.util.List;

public interface BookService {
    Long save(BookDto dto);

    List<Book> findAll();

    Book findById(Long id);

    List<Book> search(String query);

    void delete(Book book);

    void addBookToUser(Book book, User user);

    void deleteBookFromUser(Book book);

}
