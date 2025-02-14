package repositories.notifications;

import entities.notifiers.*;
import entities.users.LoggedInUser;
import entities.users.User;
import services.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotifierRepositoryImpl implements NotifierRepository {
    private final List<Observer> notifiers = new ArrayList<>();
    private final NotificationManager notificationManager;
    private final UserService userService;

    public NotifierRepositoryImpl(UserService userService, NotificationManager notificationManager) {
        this.userService = userService;
        this.notificationManager = notificationManager;
        List<User> users = userService.getUsers();

        for (User user : users) {
            UUID userId = user.getId();
            if (user.getEmail() != null) {
                Observer emailNotifier = new EmailNotifier(userId, user.getEmail());
                notifiers.add(emailNotifier);
                notificationManager.addObserver(userId, emailNotifier);
            }
            if (user instanceof LoggedInUser loggedInUser && loggedInUser.getPhoneNumber() != null) {
                Observer smsNotifier = new SmsNotifier(userId, loggedInUser.getPhoneNumber());
                notifiers.add(smsNotifier);
                notificationManager.addObserver(userId, smsNotifier);
            }
            Observer pigeonNotifier = new PigeonNotifier(userId, "Default Address");
            notifiers.add(pigeonNotifier);
            notificationManager.addObserver(userId, pigeonNotifier);
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
        // Also remove from notification manager for all users
        for (User user : userService.getUsers()) {
            notificationManager.removeObserver(user.getId(), notifier);
        }
    }
}
