package repositories.listing;

import entities.Listing;
import entities.products.Product;
import entities.users.User;
import entities.vehicles.Car;
import services.productCar.ProductCarService;
import services.user.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ListingRepositoryImpl implements ListingRepository {
    private final List<Listing> listings = new ArrayList<>();

    public ListingRepositoryImpl(ProductCarService productCarService, UserService userService) {

        List<User> users = userService.getUsers();
        List<Product> products = productCarService.getAllProducts();
        List<Car> cars = productCarService.getAllCar();

        if (users.isEmpty()) {
            throw new IllegalArgumentException("Users list cannot be empty!");
        }

        // Assign Bobby as the owner for some listings
        User bobby = users.stream()
                .filter(user -> "Bobby".equalsIgnoreCase(user.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Bobby must exist in the users list!"));

        // Assign listings to Bobby
        IntStream.range(0, products.size())
                .forEach(i -> listings.add(new Listing(
                        "Listing for " + products.get(i).getName(),
                        products.get(i).getName(),
                        true,
                        i % 2 == 0, // Alternate sponsorship
                        100.0 + Math.random() * 900.0,
                        products.get(i),
                        LocalDate.now(),
                        bobby
                )));

        // Distribute remaining listings among other users
        List<User> otherUsers = new ArrayList<>(users);
        otherUsers.remove(bobby);

        IntStream.range(0, cars.size())
                .forEach(i -> listings.add(new Listing(
                        "Listing for " + cars.get(i).getBrand() + " " + cars.get(i).getModel(),
                        cars.get(i).getBrand() + " " + cars.get(i).getModel(),
                        true,
                        true,
                        5000.0 + Math.random() * 20000.0,
                        cars.get(i),
                        LocalDate.now(),
                        otherUsers.get(i % otherUsers.size()) // Cycle through other users
                )));
    }

    @Override
    public void save(Listing listing) {
        listings.add(listing);
    }

    @Override
    public List<Listing> getAllListings() {
        return new ArrayList<>(listings);
    }
}

