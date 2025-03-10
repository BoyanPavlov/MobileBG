package entities.products;

import java.time.LocalDate;
import java.util.UUID;

public class Product {
    protected String name;
    protected String category;
    protected LocalDate createdAt;
    protected final UUID id;

    // Constructor
    public Product(String name, String category, LocalDate createdAt) {
        this.name = name;
        this.category = category;
        this.createdAt = createdAt; // Can be null
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    // toString Method
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", createdAt=" + (createdAt != null ? createdAt : "null") +
                '}';
    }
}

