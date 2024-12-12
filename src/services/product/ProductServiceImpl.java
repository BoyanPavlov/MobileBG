package services.product;

import entities.products.Product;
import repositories.product.ProductRepository;


import java.util.UUID;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
        System.out.println("Product added: " + product);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.findById(id).ifPresentOrElse(
                product -> {
                    productRepository.delete(id);
                    System.out.println("Product deleted: " + product);
                },
                () -> System.out.println("Product with ID " + id + " not found.")
        );
    }

    @Override
    public void findProductById(UUID id) {
        productRepository.findById(id).ifPresentOrElse(
                product -> System.out.println("Product found: " + product),
                () -> System.out.println("Product with ID " + id + " not found.")
        );
    }

    @Override
    public void listAllProducts() {
        productRepository.findAll().forEach(System.out::println);
    }
}
