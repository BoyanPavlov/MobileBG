package entities.filters;

public interface FieldExtractor<T, V> {
    V extractValue(T item);
}
