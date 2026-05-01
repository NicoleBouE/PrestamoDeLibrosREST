package designconquer.prestamodelibrosrest.api;

import designconquer.prestamodelibrosrest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
//@RequestMapping("/api/v1/users/{userId}/tasks")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
}