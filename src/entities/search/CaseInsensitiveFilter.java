package entities.search;

public class CaseInsensitiveFilter<T> implements Filter<T> {
    private final String valueToFind;
    private final FieldExtractor<T, String> fieldExtractor;

    public CaseInsensitiveFilter(FieldExtractor<T, String> fieldExtractor, String valueToFind) {
        this.valueToFind = valueToFind;
        this.fieldExtractor = fieldExtractor;
    }

    @Override
    public boolean matches(T item) {
        String value = fieldExtractor.extractValue(item);
        return value != null && value.trim().toLowerCase().contains(valueToFind);
    }
}

