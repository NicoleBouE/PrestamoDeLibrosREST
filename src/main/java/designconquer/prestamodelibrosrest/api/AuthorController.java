package designconquer.prestamodelibrosrest.api;

import designconquer.prestamodelibrosrest.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
//@RequestMapping("/api/v1/users/{userId}/tasks")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
}
