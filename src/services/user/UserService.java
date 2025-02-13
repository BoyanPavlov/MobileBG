package services.user;

import entities.users.User;

import java.util.List;

public interface UserService {
    User authenticateUser(String username, String password);

    List<User> getUsers();

    User registerUser(String name, String email, String password, String phone, String address);
}
