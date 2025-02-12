package services.notifications;

import entities.notifiers.Observer;

import java.util.UUID;

public interface NotificationService {
    void registerNotifier(UUID userId, Observer notifier);

    void unregisterNotifier(UUID userId, Observer notifier);

    void notifyUser(UUID userId, String title, String message); // ✅ User-specific notifications
}
