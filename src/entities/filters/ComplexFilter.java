package entities.filters;

import entities.Listing;
import entities.products.Product;
import entities.vehicles.Car;
import entities.vehicles.Vehicle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ComplexFilter implements Filter<Listing> {
    private final List<FilterGroup> groups;

    public ComplexFilter(String filterExpression) {
        this.groups = ComplexFilterParser.parse(filterExpression);
        if (groups.isEmpty()) {
            throw new IllegalArgumentException("No valid filter conditions found in expression: " + filterExpression);
        }
    }

    @Override
    public boolean matches(Listing listing) {
        if (listing == null || listing.product() == null) {
            return false;
        }
        
        // At least one group must match (OR)
        return groups.stream().anyMatch(group -> group.matches(new ListingFilterMatcher(listing)));
    }

    private static class ListingFilterMatcher implements FilterMatcher {
        private final Listing listing;
        private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

        public ListingFilterMatcher(Listing listing) {
            this.listing = listing;
        }

        @Override
        public boolean matchesCondition(FilterCondition condition) {
            Product product = listing.product();
            
            try {
                // Generic product fields
                boolean result = switch (condition.field().toLowerCase()) {
                    case "name" -> matchStringCondition(product.getName(), condition);
                    case "category" -> matchStringCondition(product.getCategory(), condition);
                    case "createdat" -> matchDateCondition(product.getCreatedAt(), condition);
                    case "price" -> matchNumberCondition(listing.price(), condition);
                    default -> matchSpecificProductFields(product, condition);
                };
                return result;
            } catch (Exception e) {
                return false;
            }
        }
        
        private boolean matchSpecificProductFields(Product product, FilterCondition condition) {
            // Vehicle-specific fields
            if (product instanceof Vehicle vehicle) {
                return switch (condition.field().toLowerCase()) {
                    case "brand" -> matchStringCondition(vehicle.getBrand(), condition);
                    case "model" -> matchStringCondition(vehicle.getModel(), condition);
                    case "year" -> matchNumberCondition(vehicle.getYear(), condition);
                    default -> false;
                };
            }
            
            // Add more product types here if needed
            
            return false;
        }

        private boolean matchStringCondition(String actual, FilterCondition condition) {
            if (actual == null || condition.value() == null) {
                return false;
            }
            return switch (condition.operator()) {
                case "=" -> actual.trim().equalsIgnoreCase(condition.value().trim());
                default -> false;
            };
        }

        private boolean matchNumberCondition(Number actual, FilterCondition condition) {
            if (actual == null) {
                return false;
            }
            try {
                double actualValue = actual.doubleValue();
                double conditionValue = Double.parseDouble(condition.value());

                return switch (condition.operator()) {
                    case "=" -> actualValue == conditionValue;
                    case ">" -> actualValue > conditionValue;
                    case "<" -> actualValue < conditionValue;
                    case ">=" -> actualValue >= conditionValue;
                    case "<=" -> actualValue <= conditionValue;
                    default -> false;
                };
            } catch (NumberFormatException e) {
                return false;
            }
        }
        
        private boolean matchDateCondition(LocalDate actual, FilterCondition condition) {
            if (actual == null) {
                return false;
            }
            try {
                LocalDate conditionValue = LocalDate.parse(condition.value(), DATE_FORMATTER);
                
                return switch (condition.operator()) {
                    case "=" -> actual.isEqual(conditionValue);
                    case ">" -> actual.isAfter(conditionValue);
                    case "<" -> actual.isBefore(conditionValue);
                    case ">=" -> !actual.isBefore(conditionValue);
                    case "<=" -> !actual.isAfter(conditionValue);
                    default -> false;
                };
            } catch (DateTimeParseException e) {
                return false;
            }
        }
    }
}
