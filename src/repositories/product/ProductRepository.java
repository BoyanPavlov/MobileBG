package repositories.product;

import entities.products.Product;

import java.util.List;

public interface ProductRepository {
    void save(Product product);

    List<Product> findAll();
}

