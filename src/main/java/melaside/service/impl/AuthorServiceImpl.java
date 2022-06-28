package melaside.service.impl;

import lombok.RequiredArgsConstructor;
import melaside.exception.AuthorNotFoundException;
import melaside.model.Author;
import melaside.repository.AuthorRepo;
import melaside.service.AuthorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    @Override
    @Transactional
    public void create(String initials) {
        Author author = new Author();

        author.setInitials(initials);

        authorRepo.save(author);
    }

    @Override
    public List<Author> findAll() {
        return (List<Author>) authorRepo.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepo.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }


}
