package repositories.notifications;

import entities.notifiers.Observer;

import java.util.List;

public interface NotifierRepository {
    List<Observer> getAllNotifiers();

    void addNotifier(Observer notifier);

    void removeNotifier(Observer notifier);
}
