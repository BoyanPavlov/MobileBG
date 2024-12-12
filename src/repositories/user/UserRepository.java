package repositories.user;

import entities.users.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);

    void delete(UUID id);

    Optional<User> findById(UUID id);

    List<User> findAll();
}

