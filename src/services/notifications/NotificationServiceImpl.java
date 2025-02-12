package services.notifications;

import entities.notifiers.NotificationManager;
import entities.notifiers.Observer;
import repositories.notifications.NotifierRepository;

import java.util.UUID;

public class NotificationServiceImpl implements NotificationService {
    private final NotificationManager notificationManager;
    private final NotifierRepository notifierRepository;

    public NotificationServiceImpl(NotificationManager notificationManager, NotifierRepository notifierRepository) {
        this.notificationManager = notificationManager;
        this.notifierRepository = notifierRepository;
    }

    @Override
    public void registerNotifier(UUID userId, Observer notifier) {
        notificationManager.addObserver(userId, notifier);
        notifierRepository.addNotifier(notifier);
    }

    @Override
    public void unregisterNotifier(UUID userId, Observer notifier) {
        notificationManager.removeObserver(userId, notifier);
        notifierRepository.removeNotifier(notifier);
    }

    @Override
    public void notifyUser(UUID userId, String title, String message) {
        notificationManager.notifyUser(userId, title, message);
    }
}
