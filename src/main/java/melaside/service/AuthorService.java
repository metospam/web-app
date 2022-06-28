package melaside.service;

import melaside.model.Author;

import java.util.List;

public interface AuthorService {

    void create(String firstName, String lastName);

    List<Author> findAll();

}
