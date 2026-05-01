package designconquer.prestamodelibrosrest.repository;

import designconquer.prestamodelibrosrest.data.Author;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AuthorRepository extends ListCrudRepository<Author, Long> {

    Author findByIdAuthor(long id);
    List<Author> findAllByNameContainingIgnoreCase(String name);
}
