package services.productCar;

import entities.products.Product;
import entities.vehicles.Car;

import java.util.List;

public interface ProductCarService {
    void addProduct(Product product);

    void addCar(Car car);

    void listProducts();

    void listCars();

    List<Car> getAllCar();

    List<Product> getAllProducts();
}
