package designconquer.prestamodelibrosrest.service;

import designconquer.prestamodelibrosrest.data.Book;
import designconquer.prestamodelibrosrest.repository.AuthorRepository;
import designconquer.prestamodelibrosrest.repository.BookGenreRepository;
import designconquer.prestamodelibrosrest.repository.BookRepository;
import designconquer.prestamodelibrosrest.repository.GenreRepository;
import designconquer.prestamodelibrosrest.service.dto.BookDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookGenreRepository bookGenreRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, BookGenreRepository bookGenreRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.bookGenreRepository = bookGenreRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public Book saveBook(Book book) {
        if (book.getCharge() < 0){
            book.setCharge(0L);
        }
        if (book.getQuantity() < 0){
            book.setQuantity(1);
        }
        return bookRepository.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return  bookRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Libro no encontrado con id: " + id));
    }

    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    if (updatedBook.getCharge() >= 0) {
                        existingBook.setCharge(updatedBook.getCharge());
                    }
                    if (updatedBook.getQuantity() >= 0) {
                        existingBook.setQuantity(updatedBook.getQuantity());
                    }
                    if (updatedBook.getAvailability() != null) {
                        existingBook.setAvailability(updatedBook.getAvailability());
                    }
                    if (updatedBook.getTitle() != null && !updatedBook.getTitle().trim().isEmpty()) {
                        existingBook.setTitle(updatedBook.getTitle().trim());
                    }
                    if (updatedBook.getIdAuthor() != null && authorRepository.findById(updatedBook.getIdAuthor()).isPresent()) {
                        existingBook.setIdAuthor(updatedBook.getIdAuthor());
                    }
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book no encontrado con el id: " + id));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book addGenreToBook(Long idGenre, Long idBook) {
        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> new RuntimeException("No se encontró el libro con ID: " + idBook));

        book.addGenre(idGenre);

        log.info("Género {} añadido exitosamente al libro {}", idGenre, idBook);
        return bookRepository.save(book);
    }

    public BookDTO getBookDTO(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        String authorName = authorRepository.findById(book.getIdAuthor())
                .map(author -> author.getName())
                .orElse("Autor desconocido");

        List<String> names = book.getGenres().stream()
                .map(bg -> genreRepository.findById(bg.getIdGenre())
                        .map(genre -> genre.getGenre())
                        .orElse("Género desconocido"))
                .collect(Collectors.toList());

        return new BookDTO(
                book.getIdBook(),
                book.getTitle(),
                book.getIdAuthor(),
                authorName,
                names,
                book.getAvailability().name()
        );
    }
}
