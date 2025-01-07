package repositories.product;

import entities.products.Product;

import java.time.LocalDate;
import java.util.*;

public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public ProductRepositoryImpl() {
        // Pre-populate with initial data
        products.add(new Product("Grill", "Car parts", LocalDate.of(2025, 1, 4)));
        products.add(new Product("Roll cage", "Car parts", LocalDate.now()));
        products.add(new Product("Tire", "Car parts", LocalDate.of(2024, 12, 4)));
        products.add(new Product("Radio", "Electronics", LocalDate.of(2024, 10, 3)));
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }
}
