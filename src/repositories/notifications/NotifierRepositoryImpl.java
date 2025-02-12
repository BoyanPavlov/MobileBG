package repositories.notifiers.impl;

import entities.notifiers.EmailNotifier;
import entities.notifiers.Observer;
import entities.notifiers.PigeonNotifier;
import entities.notifiers.SmsNotifier;
import entities.users.LoggedInUser;
import entities.users.User;
import repositories.notifications.NotifierRepository;
import services.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotifierRepositoryImpl implements NotifierRepository {
    private final List<Observer> notifiers = new ArrayList<>();

    public NotifierRepositoryImpl(UserService userService) {
        List<User> users = userService.getUsers();

        for (User user : users) {
            UUID userId = user.getId();
            if (user.getEmail() != null) {
                notifiers.add(new EmailNotifier(userId, user.getEmail()));
            }
            if (user instanceof LoggedInUser loggedInUser && loggedInUser.getPhoneNumber() != null) {
                notifiers.add(new SmsNotifier(userId, loggedInUser.getPhoneNumber()));
            }
            notifiers.add(new PigeonNotifier(userId, "Default Address"));
        }
    }

    @Override
    public List<Observer> getAllNotifiers() {
        return new ArrayList<>(notifiers);
    }

    @Override
    public void addNotifier(Observer notifier) {
        notifiers.add(notifier);
    }

    @Override
    public void removeNotifier(Observer notifier) {
        notifiers.remove(notifier);
    }
}
