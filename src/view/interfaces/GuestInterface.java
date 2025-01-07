package view.interfaces;

import entities.Listing;
import services.listing.ListingService;

import java.util.List;
import java.util.Scanner;


public class GuestInterface extends UserInterface {
    private final ListingService listingService;
    private final Scanner scanner;

    public GuestInterface(ListingService listingService) {
        this.listingService = listingService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showMenu() {
        while (true) {
            System.out.println("\n=== Guest Menu ===");
            System.out.println("1. Show All Listings");
            System.out.println("2. Search a Listing");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> showAllListings();
                case 2 -> searchListing();
                //case 3 -> viewSearchHistory();
                case 3 -> {
                    System.out.println("Exiting to main menu...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showAllListings() {
        List<Listing> listings = listingService.getAllListings();
        if (listings.isEmpty()) {
            System.out.println("No listings available.");
        } else {
            listings.forEach(System.out::println);
        }
    }

    private void searchListing() {
        while (true) {
            try {
                System.out.println("\nChoose a filter:");
                System.out.println("1. Range Filter");
                System.out.println("2. Case-Insensitive Search");
                System.out.println("3. Exact Value Search");
                System.out.print("Choose a filter: ");

                int filterChoice = Integer.parseInt(scanner.nextLine().trim()); // Secure input with parsing

                switch (filterChoice) {
                    case 1 -> {
                        applyRangeFilter();
                        return; // Exit after successful filter application
                    }
                    case 2 -> {
                        applyCaseInsensitiveSearch();
                        return;
                    }
                    case 3 -> {
                        applyExactValueSearch();
                        return;
                    }
                    default -> System.out.println("Invalid filter choice. Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private void applyRangeFilter() {
        while (true) {
            try {
                System.out.println("\n=== Choose Range Filter ===");
                System.out.println("1. Filter by Price");
                System.out.println("2. Filter by Year");
                System.out.println("3. Back to Previous Menu");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1 -> applyPriceRangeFilter();
                    case 2 -> applyYearRangeFilter();
                    case 3 -> {
                        System.out.println("Returning to previous menu...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    private void applyPriceRangeFilter() {
        double min, max;

        while (true) {
            try {
                System.out.print("Enter the minimum price: ");
                min = Double.parseDouble(scanner.nextLine().trim());

                System.out.print("Enter the maximum price: ");
                max = Double.parseDouble(scanner.nextLine().trim());

                if (min > max) {
                    System.out.println("Minimum price cannot be greater than maximum price. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }

        List<Listing> results = listingService.searchByRangePrice(min, max);
        displayResults(results);
    }

    private void applyYearRangeFilter() {
        int minYear, maxYear;

        while (true) {
            try {
                System.out.print("Enter the minimum year: ");
                minYear = Integer.parseInt(scanner.nextLine().trim());

                System.out.print("Enter the maximum year: ");
                maxYear = Integer.parseInt(scanner.nextLine().trim());

                if (minYear > maxYear) {
                    System.out.println("Minimum year cannot be greater than maximum year. Please try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid year.");
            }
        }

        List<Listing> results = listingService.searchByRangeYear(minYear, maxYear);
        displayResults(results);
    }


    private void applyCaseInsensitiveSearch() {
        System.out.print("Enter a keyword to search in titles or descriptions: ");
        String keyword = scanner.nextLine();

        List<Listing> results = listingService.searchByKeyword(keyword);
        displayResults(results);
    }

    private void applyExactValueSearch() {
        System.out.print("Enter the exact value to search in titles or descriptions: ");
        String value = scanner.nextLine();

        List<Listing> results = listingService.searchByExactValue(value);
        displayResults(results);
    }

    private void displayResults(List<Listing> results) {
        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            results.forEach(System.out::println);
        }
    }
}
