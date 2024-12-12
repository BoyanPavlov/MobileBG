package services.user;

import entities.users.User;

import java.util.UUID;

public interface UserService {
    void addUser(User user);

    void deleteUser(String id);

    void findUserById(UUID id);
}
