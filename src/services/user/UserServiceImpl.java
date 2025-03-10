package services.user;

import entities.users.LoggedInUser;
import entities.users.Role;
import entities.users.User;
import repositories.user.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticateUser(String username, String password) {
        return userRepository.getAllUsers().stream()
                .filter(u -> u.getEmail().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User registerUser(String name, String email, String password, String phone, String address) {
        if (name == null || email == null || password == null || phone == null ||
                name.isBlank() || email.isBlank() || password.isBlank() || phone.isBlank()) {
            System.out.println("❌ Registration failed. All fields are required.");
            return null;
        }

        boolean emailExists = userRepository.getAllUsers().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));

        if (emailExists) {
            System.out.println("❌ Registration failed. Email already exists.");
            return null;
        }

        LoggedInUser newUser = new LoggedInUser(name, password, email, phone, address, Role.BUYER);
        userRepository.save(newUser);
        System.out.println("✅ Registration successful! You can now log in.");
        return newUser;
    }


}
