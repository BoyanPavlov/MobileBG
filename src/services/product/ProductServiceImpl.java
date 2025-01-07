package services.product;

import entities.products.Product;
import repositories.product.ProductRepository;

import java.util.List;

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
    public void listAllProducts() {
        System.out.println("All products: ");
        productRepository.findAll().forEach(System.out::println);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
