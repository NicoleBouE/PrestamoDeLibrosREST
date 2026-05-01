package designconquer.prestamodelibrosrest.api;

import designconquer.prestamodelibrosrest.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
//@RequestMapping("/api/v1/users/{userId}/tasks")
public class GenreController {

    private final GenreService genreService;
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
}