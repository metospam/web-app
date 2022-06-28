package melaside.service.impl;

import lombok.RequiredArgsConstructor;
import melaside.model.Author;
import melaside.repository.AuthorRepo;
import melaside.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    @Override
    public void create(String firstName, String lastName) {
        Author author = new Author();

        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorRepo.save(author);
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) authorRepo.findAll();
    }


}
