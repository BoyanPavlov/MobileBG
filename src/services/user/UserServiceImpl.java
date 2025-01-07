package services.user;

import entities.users.User;
import repositories.user.UserRepository;

import java.util.List;
import java.util.Optional;

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
    public void findUserByName(String username, String password) {
        Optional<User> user = userRepository.findAll().stream()
                .filter(u -> u.getName().equals(username) && u.getPassword().equals(password))
                .findFirst();

        user.ifPresentOrElse(
                foundUser -> System.out.println("User found: " + foundUser),
                () -> System.out.println("User not found with provided credentials.")
        );
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}

