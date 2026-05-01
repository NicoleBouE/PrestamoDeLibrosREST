package designconquer.prestamodelibrosrest.api;

import designconquer.prestamodelibrosrest.service.ClientService;
import designconquer.prestamodelibrosrest.data.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

   private final ClientService clientService;

   public ClientController(ClientService clientService) {
      this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.save(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }
}