package designconquer.prestamodelibrosrest.repository;

import designconquer.prestamodelibrosrest.data.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends ListCrudRepository<User, Long> {

    User findByIdUser(long id);
    List<User> findAllByNameContainingIgnoreCase(String name);
    List<User> findAllByUsernameIgnoreCase(String username);
}