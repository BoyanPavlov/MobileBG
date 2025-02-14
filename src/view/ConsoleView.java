package view;

import entities.users.User;
import services.listing.ListingService;
import services.notifications.NotificationService;
import services.user.UserService;
import view.interfaces.GuestInterface;
import view.interfaces.LoggedUserInterface;
import view.interfaces.UserInterface;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;
    private UserInterface userInterface;
    private final ListingService listingService;
    private final UserService userService;
    private final NotificationService notificationService;

    public ConsoleView(ListingService listingService, UserService userService, NotificationService notificationService) {
        this.listingService = listingService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
        this.userInterface = null;
        this.notificationService = notificationService;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Welcome to MobileBG ===");
            System.out.println("1. Enter as Guest");
            System.out.println("2. Login");
            System.out.println("3. Register");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> userInterface = new GuestInterface(listingService);
                case "2" -> {
                    User user = handleLogin();
                    if (user != null) {
                        userInterface = new LoggedUserInterface(listingService, user);
                    }
                }
                case "3" -> handleRegistration();
                case "4" -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            if (userInterface != null) {
                userInterface.showMenu();
                userInterface = null;
            }
        }
    }

    private User handleLogin() {
        System.out.println("\n=== Login ===");

        while (true) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            User user = userService.authenticateUser(email, password);

            if (user != null) {
                System.out.println("✅ Login successful. Welcome, " + user.getName() + "!");
                notificationService.notifyUser(user.getId(), "Attention, 50% less in Fantastiko", "Did you shop yesterday, " + user.getName() + "?");
                return user;
            } else {
                System.out.println("❌ Invalid email or password. Please try again.");
            }
        }
    }

    private void handleRegistration() {
        System.out.println("\n=== Registration ===");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine().trim();

        if (!password.equals(confirmPassword)) {
            System.out.println("❌ Passwords do not match!");
            return;
        }

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine().trim();

        System.out.print("Enter your address: ");
        String address = scanner.nextLine().trim();

        User newUser = userService.registerUser(name, email, password, phone, address);

        if (newUser == null) {
            System.out.println("❌ Registration failed. Please try again.");
        }
    }

}
