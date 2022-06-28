package melaside.repository;

import melaside.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book,Long> {

    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1%")
    List<Book> search(String query);

}
