package melaside.service;

import melaside.model.Author;

import java.util.List;

public interface AuthorService {

    void create(String initials);

    List<Author> findAll();

    Author findById(Long id);

}
