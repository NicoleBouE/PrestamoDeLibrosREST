package designconquer.prestamodelibrosrest.service;


import designconquer.prestamodelibrosrest.repository.ClientRepository;
import designconquer.prestamodelibrosrest.data.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(Client client) {
        if(client.getAccountBalance() < 0){
            client.setAccountBalance(0L);
        }
        return clientRepository.save(client);
    }

    public Iterable<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return  clientRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Cliente no encontrado con id: " + id));
    }

    public Client updateClient(Long id, Client updatedClient) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    if (updatedClient.getName() != null && !updatedClient.getName().trim().isEmpty()) {
                        existingClient.setName(updatedClient.getName().trim());
                    }
                    if (updatedClient.getAccountBalance() != null && updatedClient.getAccountBalance() >= 0) {
                        existingClient.setAccountBalance(updatedClient.getAccountBalance());
                    }
                    return clientRepository.save(existingClient);
                })
                .orElseThrow(() -> new RuntimeException("Client no encontrado con el id: " + id));
    }
}