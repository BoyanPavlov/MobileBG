package view.interfaces;

import entities.users.User;
import services.listing.ListingService;
import services.user.UserService;

import java.util.Scanner;

public class LoggedUserInterface extends UserInterface {

    private final Scanner scanner;

    public LoggedUserInterface(ListingService listingService, UserService userService, User user) {
        super(listingService, user);
        this.scanner = new Scanner(System.in);
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

            String choice = scanner.nextLine().trim();

            switch (choice) {
                //case "1" -> createListing();
                //case "2" -> deleteListing();
                //case "3" -> showMyListings();
                case "4" -> showAllListings();
                case "5" -> searchListing();
                case "6" -> viewSearchHistory();
                case "7" -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

//    private void createListing() {
//        System.out.println("\n=== Create New Listing ===");
//        System.out.println("Choose listing type:");
//        System.out.println("1. Car");
//        System.out.println("2. Product");
//        System.out.print("Enter your choice (1-2): ");
//
//        String choice = scanner.nextLine().trim();
//
//        switch (choice) {
//            case "1" -> createCarListing();
//            case "2" -> createProductListing();
//            default -> System.out.println("❌ Invalid choice. Please try again.");
//        }
//    }
//
//    private void createCarListing() {
//        System.out.println("\n=== Create New Car Listing ===");
//
//        System.out.print("Enter car brand: ");
//        String brand = scanner.nextLine().trim();
//
//        System.out.print("Enter car model: ");
//        String model = scanner.nextLine().trim();
//
//        int year;
//        while (true) {
//            try {
//                System.out.print("Enter car year: ");
//                year = Integer.parseInt(scanner.nextLine().trim());
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a valid year.");
//            }
//        }
//
//        System.out.print("Is the car new? (true/false): ");
//        boolean isNew = Boolean.parseBoolean(scanner.nextLine().trim());
//
//        Car car = new Car(brand, model, year, isNew);
//
//        double price;
//        while (true) {
//            try {
//                System.out.print("Enter listing price: ");
//                price = Double.parseDouble(scanner.nextLine().trim());
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid price. Please enter a numeric value.");
//            }
//        }
//
//        System.out.print("Enter listing description: ");
//        String description = scanner.nextLine().trim();
//
//        listingService.createCarListing(user, car, price, description);
//        System.out.println("✅ Car listing created successfully!");
//    }

//    private void createProductListing() {
//        System.out.println("\n=== Create New Product Listing ===");
//
//        System.out.print("Enter product name: ");
//        String productName = scanner.nextLine().trim();
//
//        System.out.print("Enter product category: ");
//        String category = scanner.nextLine().trim();
//
//        LocalDate createdAt;
//        while (true) {
//            try {
//                System.out.print("Enter product creation date (YYYY-MM-DD): ");
//                createdAt = LocalDate.parse(scanner.nextLine().trim());
//                break;
//            } catch (DateTimeParseException e) {
//                System.out.println("Invalid date format. Please enter a valid date in YYYY-MM-DD format.");
//            }
//        }
//
//        double price;
//        while (true) {
//            try {
//                System.out.print("Enter listing price: ");
//                price = Double.parseDouble(scanner.nextLine().trim());
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid price. Please enter a numeric value.");
//            }
//        }
//
//        Product product = new Product(productName, category, createdAt);
//
//        listingService.createProductListing(user, product, price);
//        System.out.println("✅ Product listing created successfully!");
//    }


//    private void deleteListing() {
//        System.out.println("\n=== Delete Listing ===");
//        List<?> userListings = listingService.getListingsByUser(user);
//
//        if (userListings.isEmpty()) {
//            System.out.println("❌ You have no listings to delete.");
//            return;
//        }
//
//        System.out.println("Your listings:");
//        for (int i = 0; i < userListings.size(); i++) {
//            System.out.printf("%d. %s%n", i + 1, userListings.get(i));
//        }
//
//        int choice;
//        while (true) {
//            try {
//                System.out.print("Enter the number of the listing to delete (0 to cancel): ");
//                choice = Integer.parseInt(scanner.nextLine().trim());
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a number.");
//            }
//        }
//
//        if (choice == 0) {
//            System.out.println("❌ Deletion cancelled.");
//            return;
//        }
//
//        if (choice > 0 && choice <= userListings.size()) {
//            var listing = userListings.get(choice - 1);
//            listingService.deleteListing(listing);
//            System.out.println("✅ Listing deleted successfully!");
//        } else {
//            System.out.println("❌ Invalid listing number.");
//        }
//    }

//    private void showMyListings() {
//        System.out.println("\n=== My Listings ===");
//        var userListings = listingService.getListingsByUser(user);
//
//        if (userListings.isEmpty()) {
//            System.out.println("❌ You have no listings.");
//            return;
//        }
//
//        userListings.forEach(System.out::println);
//    }


}
