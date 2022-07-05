package melaside.service.impl;

import lombok.RequiredArgsConstructor;
import melaside.exception.GenreNotFoundException;
import melaside.model.Genre;
import melaside.repository.GenreRepo;
import melaside.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;

    @Override
    public Genre findById(Long id) {
        return genreRepo.findById(id).orElseThrow(() -> new GenreNotFoundException(id));
    }

    @Override
    public List<Genre> findAll() {
        return (List<Genre>) genreRepo.findAll();
    }
}
