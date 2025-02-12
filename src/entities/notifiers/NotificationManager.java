package entities.notifiers;

import java.util.*;

public class NotificationManager {
    private final Map<UUID, List<Observer>> userObservers = new HashMap<>();

    public void addObserver(UUID userId, Observer observer) {
        userObservers.computeIfAbsent(userId, k -> new ArrayList<>()).add(observer);
    }

    public void removeObserver(UUID userId, Observer observer) {
        userObservers.getOrDefault(userId, new ArrayList<>()).remove(observer);
    }

    public void notifyUser(UUID userId, String title, String message) {
        List<Observer> observers = userObservers.getOrDefault(userId, new ArrayList<>());
        for (Observer observer : observers) {
            observer.update(title, message);
        }
    }
}
