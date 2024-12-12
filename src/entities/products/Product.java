package entities.products;

import java.time.LocalDate;

public class Product {
    private String name;
    private String category;
    private LocalDate createdAt;

    // Constructor
    public Product(String name, String category, LocalDate createdAt) {
        this.name = name;
        this.category = category;
        this.createdAt = createdAt; // Can be null
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

