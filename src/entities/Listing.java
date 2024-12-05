package entities;

import java.time.LocalDate;

public record Listing(String description,
                      String title,
                      boolean isActive,
                      boolean isSponsored,

                      Product product,

                      LocalDate createdAt,
                      Seller creator) {
}
