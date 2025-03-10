package repositories.listing;

import entities.Listing;

import java.util.List;

public interface ListingRepository {
    void save(Listing listing);

    boolean removeListing(Listing listing);

    List<Listing> getAllListings();
}

