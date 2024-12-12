package services.product;

import entities.products.Product;

import java.util.UUID;

public interface ProductService {
    void addProduct(Product product);

    void deleteProduct(UUID id);

    void findProductById(UUID id);

    void listAllProducts();
}

