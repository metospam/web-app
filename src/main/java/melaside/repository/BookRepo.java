package melaside.repository;

import melaside.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book,Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1%")
    Page<Book> findAll(String query, Pageable pageable);

    Page<Book> findAll(Pageable pageable);
}
