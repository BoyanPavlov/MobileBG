package entities.filters;

public class CaseInsensitiveFilter<T> implements Filter<T> {
    private final String valueToFind;
    private final FieldExtractor<T, String> fieldExtractor;

    public CaseInsensitiveFilter(FieldExtractor<T, String> fieldExtractor, String valueToFind) {
        this.valueToFind = valueToFind == null ? "" : valueToFind.trim().toLowerCase();
        this.fieldExtractor = fieldExtractor;
    }

    @Override
    public boolean matches(T item) {
        if (item == null) return false;

        String value = fieldExtractor.extractValue(item);
        if (value == null) return false;

        return value.trim().toLowerCase().contains(valueToFind);
    }
}

