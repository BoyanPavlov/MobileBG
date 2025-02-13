package repositories.user;

import entities.users.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    List<User> getAllUsers();
}

