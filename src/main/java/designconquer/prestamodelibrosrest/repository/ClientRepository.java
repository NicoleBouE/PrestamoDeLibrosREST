package designconquer.prestamodelibrosrest.repository;

import designconquer.prestamodelibrosrest.data.Client;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ClientRepository extends ListCrudRepository<Client, Long> {

    Client findByIdClient(long id);
    List<Client> findAllByNameContainingIgnoreCase(String name);
}