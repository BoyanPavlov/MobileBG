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
        productService.addProduct(product);
        System.out.println("Product added: " + product);
    }

    @Override
    public void addCar(Car car) {
        carService.addCar(car);
        System.out.println("Car added: " + car);
    }

    @Override
    public void listProducts() {
        productService.listAllProducts();
    }

    @Override
    public void listCars() {
        carService.listAllCars();
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

