package services.listing;

import entities.Listing;
import entities.products.Product;
import entities.filters.CaseInsensitiveFilter;
import entities.filters.ExactValueFilter;
import entities.filters.Filter;
import entities.filters.RangeFilter;
import entities.users.User;
import entities.vehicles.Car;
import repositories.listing.ListingRepository;
import services.productCar.ProductCarService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ListingServiceImpl implements ListingService {
    private final ListingRepository listingRepository;
    private final ProductCarService productCarService;

    public ListingServiceImpl(ListingRepository listingRepository, ProductCarService productCarService) {
        this.listingRepository = listingRepository;
        this.productCarService = productCarService;
    }

    @Override
    public void createCarListing(String brand, String model, int year, boolean isManual, double price, boolean isSponsored, User user) {
        Car car = new Car(brand, model, year, isManual);
        productCarService.addCar(car);

        Listing listing = new Listing(
                "Car listing for " + brand + " " + model,
                brand + " " + model,
                true, // isActive
                isSponsored,
                price,
                car,
                LocalDate.now(),
                user
        );

        listingRepository.save(listing);
        System.out.println("✅ Car listing created successfully!");
    }

    @Override
    public void createProductListing(String productName, String category, LocalDate createdAt, double price, boolean isSponsored, User user) {
        Product product = new Product(productName, category, createdAt);
        productCarService.addProduct(product);

        Listing listing = new Listing(
                "Product listing for " + productName,
                productName,
                true, // isActive
                isSponsored,
                price,
                product,
                LocalDate.now(),
                user
        );

        listingRepository.save(listing);
        System.out.println("✅ Product listing created successfully!");
    }

    @Override
    public List<Listing> getAllListings() {
        return listingRepository.getAllListings();
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
        List<Listing> allListings = listingRepository.getAllListings();
        return allListings.stream()
                .filter(listing -> filters.stream().allMatch(filter -> filter.matches(listing)))
                .collect(Collectors.toList());
    }
}

