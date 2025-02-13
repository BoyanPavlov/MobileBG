package services.listing;

import entities.Listing;

import java.util.List;

public interface ListingService {

    void addListing(Listing listing);

//    void createCarListing(User user, Car car, double price, String description);
//
//    void createProductListing(User user, Product product, double price); // No description, uses date
//
//    Product getProductById(UUID productId);
//
//    List<Product> getAllProducts();
//
//    User getUserById(UUID userId);
//
//    List<User> getAllUsers();

    List<Listing> getAllListings();

    List<Listing> searchByRangeYear(int min, int max);

    List<Listing> searchByRangePrice(double min, double max);

    List<Listing> searchByKeyword(String keyword);

    List<Listing> searchByExactValue(String value);
}
