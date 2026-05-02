package designconquer.prestamodelibrosrest.service;

import designconquer.prestamodelibrosrest.data.Author;
import designconquer.prestamodelibrosrest.data.Client;
import designconquer.prestamodelibrosrest.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return  authorRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Autor no encontrado con id: " + id));
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    if (updatedAuthor.getName() != null && !updatedAuthor.getName().trim().isEmpty()) {
                        existingAuthor.setName(updatedAuthor.getName().trim());
                    }
                    return authorRepository.save(existingAuthor);
                })
                .orElseThrow(() -> new RuntimeException("Author no encontrado con el id: " + id));
    }

}
