package designconquer.prestamodelibrosrest.api;

import designconquer.prestamodelibrosrest.service.ClientService;
import designconquer.prestamodelibrosrest.data.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Client updatedClient = clientService.updateClient(id, clientDetails);
        return ResponseEntity.ok(updatedClient);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
//        clientService.delete(id);
//        return ResponseEntity.noContent().build(); // Retorna 204 No Content
//    }
}