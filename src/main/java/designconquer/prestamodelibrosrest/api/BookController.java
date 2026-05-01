package designconquer.prestamodelibrosrest.api;

import designconquer.prestamodelibrosrest.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
//@RequestMapping("/api/v1/users/{userId}/tasks")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}