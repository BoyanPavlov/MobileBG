package view.interfaces;

import entities.Listing;
import entities.users.User;
import services.listing.ListingService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

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
                case "2" -> deleteListing();
                case "3" -> showMyListings();
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

    private void deleteListing() {
        System.out.println("\n=== Delete a Listing ===");
        List<Listing> userListings = listingService.getListingsByUser(user);

        if (userListings.isEmpty()) {
            System.out.println("❌ You have no listings to delete.");
            return;
        }

        for (int i = 0; i < userListings.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, userListings.get(i));
        }

        System.out.print("\nEnter the number of the listing to delete (0 to cancel): ");

        int listingIndex;
        try {
            listingIndex = Integer.parseInt(SCANNER.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a number.");
            return;
        }

        if (listingIndex == 0) {
            System.out.println("❌ Deletion canceled.");
            return;
        }

        if (listingService.deleteListing(user, listingIndex)) {
            System.out.println("✅ Listing deleted successfully!");
        } else {
            System.out.println("❌ Invalid selection. No listing deleted.");
        }
    }

    private void showMyListings() {
        System.out.println("\n=== My Listings ===");
        List<Listing> userListings = listingService.getListingsByUser(user);

        if (userListings.isEmpty()) {
            System.out.println("❌ You have no active listings.");
            return;
        }

        userListings.forEach(System.out::println);
    }
}