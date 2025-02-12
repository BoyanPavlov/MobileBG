package entities.notifiers;

import java.util.UUID;

public class EmailNotifier implements Observer {
    private final UUID userId; // ✅ Store user ID
    private final String email;

    public EmailNotifier(UUID userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    @Override
    public UUID getUserId() {
        return userId;
    }

    @Override
    public void update(String title, String message) {
        System.out.println("Email to " + email + " | " + title + ": " + message);
    }
}
