package services.listing;

import entities.Listing;
import entities.users.User;

import java.time.LocalDate;
import java.util.List;

public interface ListingService {

    void createCarListing(String brand, String model, int year, boolean isManual, double price, boolean isSponsored, User user);

    void createProductListing(String productName, String category, LocalDate createdAt, double price, boolean isSponsored, User user);

    List<Listing> getAllListings();

    List<Listing> searchByRangeYear(int min, int max);

    List<Listing> searchByRangePrice(double min, double max);

    List<Listing> searchByKeyword(String keyword);

    List<Listing> searchByExactValue(String value);
}
