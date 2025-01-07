package services.listing;

import entities.Listing;
import entities.search.Filter;

import java.util.List;

public interface ListingService {

    void addListing(Listing listing);

    List<Listing> getAllListings();

    List<Listing> searchByRangeYear(int min, int max);

    List<Listing> searchByRangePrice(double min, double max);

    List<Listing> searchByKeyword(String keyword);

    List<Listing> searchByExactValue(String value);
}
