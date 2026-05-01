package designconquer.prestamodelibrosrest.api;

import designconquer.prestamodelibrosrest.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
//@RequestMapping("/api/v1/users/{userId}/tasks")
public class LoanController {

    private final LoanService loanService;
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
}