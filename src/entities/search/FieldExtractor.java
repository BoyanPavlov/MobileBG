package entities.search;

public interface FieldExtractor<T, V> {
    V extractValue(T item);
}
