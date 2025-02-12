package config;

import entities.notifiers.NotificationManager;
import repositories.notifications.NotifierRepository;
import services.notifications.NotificationService;
import services.notifications.NotificationServiceImpl;
import repositories.notifiers.impl.NotifierRepositoryImpl;
import services.user.UserService;

public class NotificationConfig {
    private final NotificationService notificationService;

    public NotificationConfig(UserService userService, NotificationManager notificationManager) {
        NotifierRepository notifierRepository = new NotifierRepositoryImpl(userService);
        notificationService = new NotificationServiceImpl(notificationManager, notifierRepository);
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }
}
