package melaside.repository;

import melaside.model.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepo extends CrudRepository<Genre, Long> {
}
