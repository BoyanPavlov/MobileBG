package entities.filters;

public interface FilterMatcher {
    boolean matchesCondition(FilterCondition condition);
}
