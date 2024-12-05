package entities.search;

public interface Filter<T> {
    boolean matches(T item);
}
