package services.user;

import entities.users.User;
import repositories.user.UserRepository;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        System.out.println("User added: " + user);
    }

    @Override
    public void deleteUser(String id) {
        UUID uuid = UUID.fromString(id);
        if (userRepository.findById(uuid).isPresent()) {
            userRepository.delete(uuid);
            System.out.println("User with ID " + id + " deleted.");
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
    }

    @Override
    public void findUserById(UUID id) {
        userRepository.findById(id)
                .ifPresentOrElse(
                        user -> System.out.println("User found: " + user),
                        () -> System.out.println("User with ID " + id + " not found.")
                );
    }
}

