package entities.filters;

import java.util.List;

public record FilterGroup(List<FilterCondition> conditions) {
    public boolean matches(FilterMatcher matcher) {
        // All conditions within a group must match (AND)
        return conditions.stream().allMatch(matcher::matchesCondition);
    }
}
