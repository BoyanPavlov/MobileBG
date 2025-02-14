package entities.filters;

import entities.Listing;
import entities.vehicles.Car;

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

        public ListingFilterMatcher(Listing listing) {
            this.listing = listing;
        }

        @Override
        public boolean matchesCondition(FilterCondition condition) {
            if (!(listing.product() instanceof Car car)) {
                return false;
            }

            try {
                return switch (condition.field().toLowerCase()) {
                    case "brand" -> matchStringCondition(car.getBrand(), condition);
                    case "model" -> matchStringCondition(car.getModel(), condition);
                    case "year" -> matchNumberCondition(car.getYear(), condition);
                    case "price" -> matchNumberCondition(listing.price(), condition);
                    default -> false;
                };
            } catch (Exception e) {
                return false;
            }
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
    }
}
