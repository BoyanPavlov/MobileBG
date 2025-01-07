package repositories.user;

import entities.users.LoggedInUser;
import entities.users.Role;
import entities.users.User;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepositoryImpl() {
        users.add(new LoggedInUser("Alice", "password123", "alice@example.com", "555-1234", "123 Main St", EnumSet.of(Role.BUYER, Role.SELLER)));
        users.add(new LoggedInUser("Bobby", "123", "bobby@example.com", "555-5678", "456 Elm St"));
        users.add(new LoggedInUser("Charlie", "pwd123", "charlie@example.com", "555-6789", "789 Oak St", Role.DEALER));
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }
}
