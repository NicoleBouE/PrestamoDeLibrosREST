package designconquer.prestamodelibrosrest.repository;

import designconquer.prestamodelibrosrest.data.Book;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends ListCrudRepository<Book, Long> {

    Book findByIdBook(long id);
    List<Book> findAllByAvailability(Book.Availability availability);
    List<Book> findAllByIdAuthor(Long idAuthor);
    List<Book> findAllByTitleContainingIgnoreCase(String title);

    @Query("SELECT b.* FROM Book b " +
            "JOIN BookGenre bg ON b.idBook = bg.idBook " +
            "JOIN Genre g ON bg.idGenre = g.idGenre " +
            "WHERE g.genre = :genre")
    List<Book> findByGenre(@Param("genre") String genre);
}
