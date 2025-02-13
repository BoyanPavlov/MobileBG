package entities.filters;

public interface Filter<T> {
    boolean matches(T item);
}
