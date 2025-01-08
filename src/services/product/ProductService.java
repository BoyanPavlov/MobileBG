package services.product;

import entities.products.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    void listAllProducts();

    List<Product> getProducts();
}

