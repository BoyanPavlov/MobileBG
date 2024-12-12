package repositories.user;

import entities.users.User;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {
    private final Map<UUID, User> userStorage = new HashMap<>();

    @Override
    public void save(User user) {
        userStorage.put(user.getId(), user);
    }

    @Override
    public void delete(UUID id) {
        userStorage.remove(id);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStorage.values());
    }
}
