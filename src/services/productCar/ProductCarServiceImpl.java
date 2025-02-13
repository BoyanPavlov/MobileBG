package services.productCar;

import entities.products.Product;
import entities.vehicles.Car;
import services.car.CarService;
import services.product.ProductService;

import java.util.List;

public class ProductCarServiceImpl implements ProductCarService {
    private final ProductService productService;
    private final CarService carService;

    public ProductCarServiceImpl(ProductService productService, CarService carService) {
        this.productService = productService;
        this.carService = carService;
    }

    @Override
    public void addProduct(Product product) {
        if (product == null) {
            System.out.println("❌ Cannot add a null product.");
            return;
        }
        productService.addProduct(product);
        System.out.println("✅ Product added: " + product);
    }

    @Override
    public void addCar(Car car) {
        if (car == null) {
            System.out.println("❌ Cannot add a null car.");
            return;
        }
        carService.addCar(car);
        System.out.println("✅ Car added: " + car);
    }

    @Override
    public void listProducts() {
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\n=== Products ===");
            products.forEach(System.out::println);
        }
    }

    @Override
    public void listCars() {
        List<Car> cars = carService.getCars();
        if (cars.isEmpty()) {
            System.out.println("No cars available.");
        } else {
            System.out.println("\n=== Cars ===");
            cars.forEach(System.out::println);
        }
    }

    @Override
    public List<Car> getAllCar() {
        return carService.getCars();
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }
}
