package repositories.product;

import entities.products.Product;

import java.util.*;

public class ProductRepositoryImpl implements ProductRepository {
    private final Map<UUID, Product> productStorage = new HashMap<>();

    @Override
    public void save(Product product) {
        productStorage.put(product.getId(), product);
    }

    @Override
    public void delete(UUID id) {
        productStorage.remove(id);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(productStorage.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productStorage.values());
    }
}
