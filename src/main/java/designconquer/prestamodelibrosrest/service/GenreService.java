package designconquer.prestamodelibrosrest.service;

import designconquer.prestamodelibrosrest.data.Client;
import designconquer.prestamodelibrosrest.data.Genre;
import designconquer.prestamodelibrosrest.data.User;
import designconquer.prestamodelibrosrest.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Iterable<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Género no encontrado con id: " + id));
    }

    public Genre updateGenre(Long id, Genre updatedGenre) {
        return genreRepository.findById(id)
                .map(existingGenre -> {
                    if (updatedGenre.getGenre() != null && !updatedGenre.getGenre().trim().isEmpty()) {
                        existingGenre.setGenre(updatedGenre.getGenre().trim());
                    }
                    return genreRepository.save(existingGenre);
                })
                .orElseThrow(() -> new RuntimeException("Genre no encontrado con el id: " + id));
    }
}