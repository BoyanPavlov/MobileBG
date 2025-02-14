package view.interfaces;

import entities.Listing;
import entities.users.User;
import history.SearchHistory;
import history.SearchMemento;
import services.listing.ListingService;

import java.util.List;
import java.util.Scanner;

public abstract class UserInterface {
    protected final User user;
    protected final ListingService listingService;
    protected final SearchHistory searchHistory;
    protected static final Scanner SCANNER = new Scanner(System.in);

    public UserInterface(ListingService listingService, User user) {
        this.user = user;
        this.listingService = listingService;
        this.searchHistory = new SearchHistory();
    }

    public abstract void showMenu();

    protected void showAllListings() {
        List<Listing> listings = listingService.getAllListings();
        if (listings.isEmpty()) {
            System.out.println("No listings available.");
        } else {
            listings.forEach(System.out::println);
        }
    }

    protected void searchListing() {
        while (true) {
            try {
                System.out.println("\nChoose a filter:");
                System.out.println("1. Range Filter");
                System.out.println("2. Case-Insensitive Search");
                System.out.println("3. Exact Value Search");
                System.out.println("4. Complex Filter");
                System.out.print("Choose a filter: ");

                int filterChoice = Integer.parseInt(SCANNER.nextLine().trim()); // Secure input with parsing

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
                    case 4 -> {
                        applyComplexFilter();
                        return;
                    }
                    default -> System.out.println("Invalid filter choice. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    protected void viewSearchHistory() {
        List<SearchMemento> history = searchHistory.getHistory();

        if (history.isEmpty()) {
            System.out.println("No search history available.");
            return;
        }

        while (true) {
            System.out.println("\n=== Search History ===");
            for (int i = 0; i < history.size(); i++) {
                SearchMemento memento = history.get(i);
                System.out.println((i + 1) + ". " + memento.getFilterDescription());
            }

            System.out.print("Enter the number of a search to view its results, or any other key to go back: ");
            String input = SCANNER.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);

                if (choice > 0 && choice <= history.size()) {
                    SearchMemento selectedMemento = history.get(choice - 1);
                    displayResults(selectedMemento.getResults());
                } else if (choice == 0) {
                    System.out.println("Returning to previous menu...");
                    return;
                } else {
                    System.out.println("Invalid choice. Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                // Non-numeric input exits the menu
                System.out.println("Exiting search history menu...");
                return;
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

                int choice = Integer.parseInt(SCANNER.nextLine().trim());

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
                min = Double.parseDouble(SCANNER.nextLine().trim());

                System.out.print("Enter the maximum price: ");
                max = Double.parseDouble(SCANNER.nextLine().trim());

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

        saveSearchHistory("Price range: " + min + " to " + max, results);
    }

    private void applyYearRangeFilter() {
        int minYear, maxYear;

        while (true) {
            try {
                System.out.print("Enter the minimum year: ");
                minYear = Integer.parseInt(SCANNER.nextLine().trim());

                System.out.print("Enter the maximum year: ");
                maxYear = Integer.parseInt(SCANNER.nextLine().trim());

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

        saveSearchHistory("Year range: " + minYear + " to " + maxYear, results);
    }


    private void applyCaseInsensitiveSearch() {
        System.out.print("Enter a keyword to search in titles or descriptions: ");
        String keyword = SCANNER.nextLine();

        List<Listing> results = listingService.searchByKeyword(keyword);
        displayResults(results);

        saveSearchHistory("Keyword search: " + keyword, results);
    }

    private void applyExactValueSearch() {
        System.out.print("Enter the exact value to search in titles or descriptions: ");
        String value = SCANNER.nextLine();

        List<Listing> results = listingService.searchByExactValue(value);
        displayResults(results);

        saveSearchHistory("Exact value search: " + value, results);
    }

    private void applyComplexFilter() {
        System.out.println("\n=== Complex Filter ===");
        System.out.println("Enter your filter expression. You can use simple or detailed format.");
        System.out.println("\nGeneral product filters:");
        System.out.println("name = 'product name'");
        System.out.println("category = 'Vehicle'");
        System.out.println("price < 50000");
        System.out.println("createdAt > 2024-01-01");
        
        System.out.println("\nVehicle-specific filters:");
        System.out.println("bmw && year > 2000");
        System.out.println("bmw || audi");
        System.out.println("brand = 'BMW' && year > 2015");
        System.out.println("price < 50000 && year >= 2020");
        
        System.out.println("\nCombined filters:");
        System.out.println("category = 'Vehicle' && price < 50000");
        System.out.println("name = 'Special Edition' && year > 2020");
        
        System.out.println("\nSupported fields:");
        System.out.println("- General: name, category, price, createdAt");
        System.out.println("- Vehicle: brand, model, year");
        System.out.println("\nSupported operators: =, >, <, >=, <=");
        System.out.println("Logical operators: && (AND), || (OR)");
        System.out.print("\nEnter your filter expression: ");

        String filterExpression = SCANNER.nextLine().trim();

        List<Listing> results = listingService.searchByComplexFilter(filterExpression);
        displayResults(results);

        saveSearchHistory("Complex filter: " + filterExpression, results);
    }

    private void displayResults(List<Listing> results) {
        if (results.isEmpty()) {
            System.out.println("No results found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private void saveSearchHistory(String filterDescription, List<Listing> results) {
        if (results.isEmpty()) {
            System.out.println("No results to save in history.");
            return;
        }

        SearchMemento memento = new SearchMemento(filterDescription, results);
        searchHistory.addMemento(memento);
    }
}
