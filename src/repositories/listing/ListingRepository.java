package repositories.listing;

import entities.Listing;

import java.util.List;

public interface ListingRepository {
    void save(Listing listing);

    List<Listing> findAll();
}

