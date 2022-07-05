package melaside.service;

import melaside.model.Book;
import melaside.model.User;
import melaside.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Long saveDto(BookDto dto);

    List<Book> findAll();

    Book findById(Long id);

    void delete(Book book);

    void addBookToUser(Book book, User user);

    void deleteBookFromUser(Book book, User user);

    Long save(Book book);

    Page<Book> findPaginated(String query, Pageable pageable);

}
