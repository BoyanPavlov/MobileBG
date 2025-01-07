package services.listing;

import entities.Listing;
import entities.products.Product;
import entities.search.CaseInsensitiveFilter;
import entities.search.ExactValueFilter;
import entities.search.Filter;
import entities.search.RangeFilter;
import repositories.listing.ListingRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ListingServiceImpl implements ListingService {
    private final ListingRepository listingRepository;

    public ListingServiceImpl(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Override
    public void addListing(Listing listing) {
        listingRepository.save(listing);
        System.out.println("Listing added: " + listing);
    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    private Integer extractCreatedAtYear(Object product) {
        if (product instanceof Product p) {
            return p.getCreatedAt().getYear(); // Extract the year from createdAt
        }
        return null; // Return null for unsupported product types
    }

    @Override
    public List<Listing> searchByRangeYear(int min, int max) {
        Filter<Listing> yearFilter = new RangeFilter<>(
                listing -> extractCreatedAtYear(listing.product()),
                (int) min, (int) max
        );

        return filterListings(List.of(yearFilter));
    }

    @Override
    public List<Listing> searchByRangePrice(double min, double max) {
        Filter<Listing> priceFilter = new RangeFilter<>(Listing::price, min, max);

        return filterListings(List.of(priceFilter));
    }

    @Override
    public List<Listing> searchByKeyword(String keyword) {
        Filter<Listing> titleFilter = new CaseInsensitiveFilter<>(Listing::title, keyword);
        Filter<Listing> descriptionFilter = new CaseInsensitiveFilter<>(Listing::description, keyword);

        return filterListings(List.of(titleFilter, descriptionFilter));
    }

    @Override
    public List<Listing> searchByExactValue(String value) {
        Filter<Listing> titleFilter = new ExactValueFilter<>(Listing::title, value);
        Filter<Listing> descriptionFilter = new ExactValueFilter<>(Listing::description, value);

        return filterListings(List.of(titleFilter, descriptionFilter));
    }

    private List<Listing> filterListings(List<Filter<Listing>> filters) {
        List<Listing> allListings = listingRepository.findAll();
        return allListings.stream()
                .filter(listing -> filters.stream().allMatch(filter -> filter.matches(listing)))
                .collect(Collectors.toList());
    }
}

