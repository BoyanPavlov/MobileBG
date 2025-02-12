package entities.notifiers;

import java.util.UUID;

public class PigeonNotifier implements Observer {
    private final UUID userId;
    private final String address;

    public PigeonNotifier(UUID userId, String address) {
        this.userId = userId;
        this.address = address;
    }

    @Override
    public UUID getUserId() {
        return userId;
    }

    @Override
    public void update(String title, String message) {
        System.out.println("Pigeon sent to " + address + " | " + title + ": " + message);
    }
}
