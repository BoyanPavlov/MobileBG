package services.user;

import entities.users.User;
import repositories.user.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        if (userRepository.findAll().stream().anyMatch(u -> u.getName().equals(user.getName()))) {
            throw new IllegalArgumentException("Username already exists");
        }
        userRepository.save(user);
        System.out.println("User registered: " + user.getName());
    }

    @Override
    public User authenticateUser(String username, String password) {
        return userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public User getUserById(UUID userId) {
        return userRepository.findAll().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
