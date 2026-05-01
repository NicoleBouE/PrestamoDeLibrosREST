package designconquer.prestamodelibrosrest.service;


import designconquer.prestamodelibrosrest.data.Book;
import designconquer.prestamodelibrosrest.repository.BookGenreRepository;
import designconquer.prestamodelibrosrest.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookGenreRepository bookGenreRepository;

    public BookService(BookRepository bookRepository, BookGenreRepository bookGenreRepository) {
        this.bookRepository = bookRepository;
        this.bookGenreRepository = bookGenreRepository;
    }

    public void addGenreToBook(Long idGenre, Long idBook) {
        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> new RuntimeException("No se encontró el libro con ID: " + idBook));

        book.addGenre(idGenre);

        bookRepository.save(book);
        log.info("Género {} añadido exitosamente al libro {}", idGenre, idBook);
    }
}