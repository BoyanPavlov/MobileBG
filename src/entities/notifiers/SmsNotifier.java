package entities.notifiers;

import java.util.UUID;

public class SmsNotifier implements Observer {
    private final UUID userId;
    private final String phoneNumber;

    public SmsNotifier(UUID userId, String phoneNumber) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public UUID getUserId() {
        return userId;
    }

    @Override
    public void update(String title, String message) {
        System.out.println("SMS to " + phoneNumber + " | " + title + ": " + message);
    }
}
