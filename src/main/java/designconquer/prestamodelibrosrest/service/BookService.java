package designconquer.prestamodelibrosrest.service;

import designconquer.prestamodelibrosrest.data.Book;
import designconquer.prestamodelibrosrest.data.Client;
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

    public Book saveBook(Book book) {
        if (book.getCharge() < 0){
            book.setCharge(0L);
        }
        if (book.getQuantity() < 0){
            book.setQuantity(1);
        }
        return bookRepository.save(book);
    }

    public void addGenreToBook(Long idGenre, Long idBook) {
        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> new RuntimeException("No se encontró el libro con ID: " + idBook));

        book.addGenre(idGenre);

        bookRepository.save(book);
        log.info("Género {} añadido exitosamente al libro {}", idGenre, idBook);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return  bookRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Libro no encontrado con id: " + id));
    }

    public Book bookClient(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    if (updatedBook.getCharge() >= 0) {
                        existingBook.setCharge(updatedBook.getCharge());
                    }
                    if (updatedBook.getQuantity() >= 0) {
                        existingBook.setQuantity(updatedBook.getQuantity());
                    }


                    if (updatedBook.getTitle() != null && !updatedBook.getTitle().trim().isEmpty()) {
                        existingBook.setTitle(updatedBook.getTitle().trim());
                    }


                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Book no encontrado con el id: " + id));
    }
}
