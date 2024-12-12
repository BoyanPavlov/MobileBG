package repositories.product;

import entities.products.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    void save(Product product);

    void delete(UUID id);

    Optional<Product> findById(UUID id);

    List<Product> findAll();
}

