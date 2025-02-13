package view.interfaces;

import services.listing.ListingService;


public class GuestInterface extends UserInterface {

    public GuestInterface(ListingService listingService) {
        super(listingService, null);
    }

    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Guest Menu ===");
            System.out.println("1. Show All Listings");
            System.out.println("2. Search a Listing");
            System.out.println("3. View Search History");
            System.out.println("4. Exit to Main Menu");
            System.out.print("Choose an option: ");

            int choice = SCANNER.nextInt();
            SCANNER.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> showAllListings();
                case 2 -> searchListing();
                case 3 -> viewSearchHistory();
                case 4 -> {
                    System.out.println("Exiting to main menu...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
