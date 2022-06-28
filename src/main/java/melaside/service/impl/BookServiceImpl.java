package melaside.service.impl;

import melaside.exception.BookNotFoundException;
import melaside.model.Author;
import melaside.model.Book;
import melaside.model.User;
import melaside.model.dto.BookDto;
import melaside.repository.BookRepo;
import melaside.service.AuthorService;
import melaside.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorService authorService;

    @Override
    @Transactional
    public Long save(BookDto dto) {
        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setDescription(dto.getDescription());

        if (dto.getAuthorId() != -1) {
            book.setAuthor(authorService.findById(dto.getAuthorId()));
        } else {
            Author author = new Author();
            author.setInitials(dto.getAuthorInitials());
            book.setAuthor(author);
        }

        bookRepo.save(book);

        return book.getId();
    }

    @Override
    public List<Book> findAll() {
        return (List<Book>) bookRepo.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public List<Book> search(String query) {
        if(query == null){
            return (List<Book>) bookRepo.findAll();
        }
        return bookRepo.search(query);
    }

    @Override
    public void delete(Book book) {
        bookRepo.delete(book);
    }

    @Override
    public void addBookToUser(Book book, User user) {
        book.setUser(user);

        bookRepo.save(book);
    }

    @Override
    public void deleteBookFromUser(Book book) {
        book.setUser(null);

        bookRepo.save(book);
    }

}
