package view.interfaces;

import entities.users.User;
import services.listing.ListingService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LoggedUserInterface extends UserInterface {

    public LoggedUserInterface(ListingService listingService, User user) {
        super(listingService, user);
    }

    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Logged User Menu ===");
            System.out.println("1. Create a listing");
            System.out.println("2. Delete a listing");
            System.out.println("3. Show my listings");
            System.out.println("4. Show all listings");
            System.out.println("5. Search listings");
            System.out.println("6. View search history");
            System.out.println("7. Logout");
            System.out.print("\nEnter your choice (1-7): ");

            String choice = SCANNER.nextLine().trim();

            switch (choice) {
                case "1" -> createListing();
                case "4" -> showAllListings();
                case "5" -> searchListing();
                case "6" -> viewSearchHistory();
                case "7" -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private void createListing() {
        System.out.println("\n=== Create New Listing ===");
        System.out.println("Choose listing type:");
        System.out.println("1. Car");
        System.out.println("2. Product");
        System.out.print("Enter your choice (1-2): ");

        String choice = SCANNER.nextLine().trim();

        switch (choice) {
            case "1" -> createCarListing();
            case "2" -> createProductListing();
            default -> System.out.println("❌ Invalid choice. Please try again.");
        }
    }

    private void createCarListing() {
        System.out.println("\n=== Create New Car Listing ===");

        System.out.print("Enter car brand: ");
        String brand = SCANNER.nextLine().trim();

        System.out.print("Enter car model: ");
        String model = SCANNER.nextLine().trim();

        int year;
        while (true) {
            try {
                System.out.print("Enter car year: ");
                year = Integer.parseInt(SCANNER.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid year.");
            }
        }

        System.out.print("Is the car manual? (true/false): ");
        boolean isManual = Boolean.parseBoolean(SCANNER.nextLine().trim());

        double price;
        while (true) {
            try {
                System.out.print("Enter listing price: ");
                price = Double.parseDouble(SCANNER.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid price. Please enter a numeric value.");
            }
        }

        System.out.print("Do you want to sponsor the listing? (true/false): ");
        boolean isSponsored = Boolean.parseBoolean(SCANNER.nextLine().trim());

        listingService.createCarListing(brand, model, year, isManual, price, isSponsored, user);
    }

    private void createProductListing() {
        System.out.println("\n=== Create New Product Listing ===");

        System.out.print("Enter product name: ");
        String productName = SCANNER.nextLine().trim();

        System.out.print("Enter product category: ");
        String category = SCANNER.nextLine().trim();

        LocalDate createdAt;
        while (true) {
            try {
                System.out.print("Enter product creation date (YYYY-MM-DD): ");
                createdAt = LocalDate.parse(SCANNER.nextLine().trim());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid date format. Please enter a valid date in YYYY-MM-DD format.");
            }
        }

        double price;
        while (true) {
            try {
                System.out.print("Enter listing price: ");
                price = Double.parseDouble(SCANNER.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid price. Please enter a numeric value.");
            }
        }

        System.out.print("Do you want to sponsor the listing? (true/false): ");
        boolean isSponsored = Boolean.parseBoolean(SCANNER.nextLine().trim());

        listingService.createProductListing(productName, category, createdAt, price, isSponsored, user);
    }
}