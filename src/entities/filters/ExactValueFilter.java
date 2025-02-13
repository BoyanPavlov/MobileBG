package entities.filters;

import java.util.Objects;

public class ExactValueFilter<T, V> implements Filter<T> {
    private final V valueToFind;
    private final FieldExtractor<T, V> fieldExtractor;

    public ExactValueFilter(FieldExtractor<T, V> fieldExtractor, V valueToFind) {
        this.valueToFind = valueToFind;
        this.fieldExtractor = fieldExtractor;
    }

    @Override
    public boolean matches(T item) {
        if (item == null) return false;

        V value = fieldExtractor.extractValue(item);
        return Objects.equals(value, valueToFind);
    }
}
