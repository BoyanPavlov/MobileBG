package services.user;

import entities.users.LoggedInUser;
import entities.users.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    User authenticateUser(String username, String password);

    List<User> getUsers();

    User registerUser(String name, String email, String password, String phone, String address);
}
