package designconquer.prestamodelibrosrest.repository;

import designconquer.prestamodelibrosrest.data.Genre;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface GenreRepository extends ListCrudRepository<Genre, Long> {

    Genre findByIdGenre(long id);
    List<Genre> findAllByGenreContainingIgnoreCase(String genre);
}