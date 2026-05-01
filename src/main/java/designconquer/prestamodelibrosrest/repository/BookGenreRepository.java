package designconquer.prestamodelibrosrest.repository;

import designconquer.prestamodelibrosrest.data.intermediate.BookGenre;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface BookGenreRepository extends ListCrudRepository<BookGenre, Long> {

    BookGenre findByIdBookGenre(long id);
    List<BookGenre> findAllByIdGenre(long id);
    List<BookGenre> findAllByIdBook(long id);
}
