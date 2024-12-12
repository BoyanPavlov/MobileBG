import entities.products.Product;
import entities.users.LoggedInUser;
import entities.users.Role;
import entities.users.User;
import entities.vehicles.Car;
import repositories.car.CarRepository;
import repositories.car.CarRepositoryImpl;
import repositories.product.ProductRepository;
import repositories.product.ProductRepositoryImpl;
import repositories.user.UserRepository;
import repositories.user.UserRepositoryImpl;
import services.car.CarService;
import services.car.CarServiceImpl;
import services.product.ProductService;
import services.product.ProductServiceImpl;
import services.user.UserService;
import services.user.UserServiceImpl;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
//    public static void main(String[] args) {
//        Car car = new Car("Toyota", "Corolla", 2021, true);
//        Car car2 = new Car("Bmw", "e60", 2000, false);
//
//        // всичките хиляди коли които имаме
//        List<Car> cars = List.of(car, car2);
//
//        List<Filter<Car>> filters = List.of(
//                new ExactValueFilter<>(Car::getBrand, "Toyota"),
//                new CaseInsensitiveFilter<>(Car::getModel, "Corolla"),
//                new RangeFilter<>(Car::getYear, 2000, 2022),
//                new RangeFilter<>(Car::getBrand, "Bmw", "Toyota")
//        );
//
//        // само колите от филтрите
//        List<Car> matchingCars = filterCars(cars, filters);
//        System.out.println("Matching cars:");
//        matchingCars.forEach(System.out::println);
//    }
//
//    // не му е мястото тук, само за демонстративни цели е
//    private static List<Car> filterCars(List<Car> cars, List<Filter<Car>> filters) {
//        return cars.stream()
//                .filter(car -> filters.stream().allMatch(filter -> filter.matches(car)))
//                .toList();
//    }

//
//    public static void main(String[] args) {
//        // Create a logged-in user with multiple roles
//        LoggedInUser multiRoleUser = new LoggedInUser(
//                "Alice",
//                "password123",
//                "alice@example.com",
//                "555-1234",
//                "123 Main St",
//                EnumSet.of(Role.BUYER, Role.SELLER)
//        );
//
//        // Add another role
//        multiRoleUser.addRole(Role.DEALER);
//
//        // Print user details
//        System.out.println(multiRoleUser);
//
//        // Remove a role
//        multiRoleUser.removeRole(Role.SELLER);
//        System.out.println("After removing SELLER role: " + multiRoleUser);
//    }


//    public static void main(String[] args) {
//        // Create repository and service
//        UserRepository userRepository = new UserRepositoryImpl();
//        UserService userService = new UserServiceImpl(userRepository);
//
//        // Create some users
//        User buyerSeller = new LoggedInUser("Alice", "password123", "alice@example.com", "555-1234", "123 Main St", Set.of(Role.BUYER, Role.SELLER));
//        User buyer = new LoggedInUser("Bob", "securepass", "bob@example.com", "555-5678", "456 Elm St");
//
//        // Add users
//        userService.addUser(buyerSeller);
//        userService.addUser(buyer);
//
//        // Find a user by ID
//        userService.findUserById(buyerSeller.getId());
//
//        // Delete a user
//        userService.deleteUser(buyerSeller.getId().toString());
//
//        // Attempt to find deleted user
//        userService.findUserById(buyerSeller.getId());
//    }

//    public static void main(String[] args) {
//        CarRepository carRepository = new CarRepositoryImpl();
//        CarService carService = new CarServiceImpl(carRepository);
//
//        // Create cars
//        Car car1 = new Car("Toyota", "Corolla", 2020, true);
//        Car car2 = new Car("Honda", "Civic", 2021, false);
//
//        // Add cars
//        carService.addCar(car1);
//        carService.addCar(car2);
//
//        // Find a car by ID
//        carService.findCarById(car1.getId());
//
//        // List all cars
//        System.out.println("Listing all cars:");
//        carService.listAllCars();
//
//        // Delete a car
//        carService.deleteCar(car1.getId());
//
//        // Attempt to find the deleted car
//        carService.findCarById(car1.getId());
//
//        // List all cars after deletion
//        System.out.println("Listing all cars after deletion:");
//        carService.listAllCars();
//    }


    public static void main(String[] args) {
        // Create repository and service
        ProductRepository productRepository = new ProductRepositoryImpl();
        ProductService productService = new ProductServiceImpl(productRepository);

        // Create products
        Product product1 = new Product("Smartphone", "Electronics", LocalDate.now());
        Product product2 = new Product("Laptop", "Computers", LocalDate.of(2023, 1, 15));

        // Add products
        productService.addProduct(product1);
        productService.addProduct(product2);

        // Find a product by ID
        productService.findProductById(product1.getId());

        // List all products
        System.out.println("Listing all products:");
        productService.listAllProducts();

        // Delete a product
        productService.deleteProduct(product1.getId());

        // Attempt to find the deleted product
        productService.findProductById(product1.getId());

        // List all products after deletion
        System.out.println("Listing all products after deletion:");
        productService.listAllProducts();
    }
}