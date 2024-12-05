package entities.search.search;

public interface FieldExtractor<T, V> {
    V extractValue(T item);
}
