package designconquer.prestamodelibrosrest.service;

import designconquer.prestamodelibrosrest.data.User;
import designconquer.prestamodelibrosrest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User no encontrado con el id: " + id));
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    if (updatedUser.getUsername() != null && !updatedUser.getUsername().trim().isEmpty()) {
                        existingUser.setUsername(updatedUser.getUsername().trim());
                    }
                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().trim().isEmpty()) {
                        existingUser.setPassword(updatedUser.getPassword().trim());
                    }
                    if (updatedUser.getRole() != null) {
                        existingUser.setRole(updatedUser.getRole());
                    }

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User no encontrado con el id: " + id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}