package services.product;

import entities.products.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void addProduct(Product product);

    void listAllProducts();

    List<Product> getProducts();
}

