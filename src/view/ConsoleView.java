package view;

import services.listing.ListingService;
import view.interfaces.GuestInterface;
import view.interfaces.UserInterface;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;
    private UserInterface userInterface;
    private final ListingService listingService;

    public ConsoleView(ListingService listingService) {
        this.listingService = listingService;
        this.scanner = new Scanner(System.in);
        this.userInterface = null;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Welcome to the System ===");
            System.out.println("1. Enter as Guest");
            System.out.println("2. Register");
            System.out.println("3. Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> userInterface = new GuestInterface(listingService);
                //case 2 -> userInterface = new LoggedUserInterface(listingService) ;
                //case 3 -> userInterface = new RegisterInterface() ;
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            // Show menu for the selected interface
            if (userInterface != null) {
                userInterface.enter();
            }
        }
    }
}

