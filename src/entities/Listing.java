package entities;

import entities.products.Product;
import entities.users.User;

import java.time.LocalDate;

public record Listing(
        String description,
        String title,
        boolean isActive,
        boolean isSponsored,
        double price,

        Product product,

        LocalDate createdAt,
        User creator) {
}
