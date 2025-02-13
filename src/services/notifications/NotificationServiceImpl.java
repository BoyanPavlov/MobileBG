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
        if (userId == null || notifier == null) {
            throw new IllegalArgumentException("User ID and notifier cannot be null");
        }
        
        // Check if notifier is already registered
        if (notifierRepository.getAllNotifiers().contains(notifier)) {
            throw new IllegalArgumentException("Notifier is already registered");
        }
        
        notificationManager.addObserver(userId, notifier);
        notifierRepository.addNotifier(notifier);
    }

    @Override
    public void unregisterNotifier(UUID userId, Observer notifier) {
        if (userId == null || notifier == null) {
            throw new IllegalArgumentException("User ID and notifier cannot be null");
        }
        
        // Check if notifier exists before removing
        if (!notifierRepository.getAllNotifiers().contains(notifier)) {
            throw new IllegalArgumentException("Notifier is not registered");
        }
        
        notificationManager.removeObserver(userId, notifier);
        notifierRepository.removeNotifier(notifier);
    }

    @Override
    public void notifyUser(UUID userId, String title, String message) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Notification title cannot be empty");
        }
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Notification message cannot be empty");
        }
        
        notificationManager.notifyUser(userId, title.trim(), message.trim());
    }
}
