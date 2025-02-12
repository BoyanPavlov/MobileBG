package entities.notifiers;

import java.util.UUID;

public interface Observer {
    UUID getUserId();  // ✅ Add this method for user-specific notifications

    void update(String title, String message);
}
