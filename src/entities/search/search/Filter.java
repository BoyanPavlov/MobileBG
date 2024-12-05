package entities.search.search;

public interface Filter<T> {
    boolean matches(T item);
}
