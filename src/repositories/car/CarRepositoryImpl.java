package repositories.car;

import entities.vehicles.Car;

import java.util.*;

public class CarRepositoryImpl implements CarRepository {
    private final Map<UUID, Car> carStorage = new HashMap<>();

    @Override
    public void save(Car car) {
        carStorage.put(car.getId(), car);
    }

    @Override
    public void delete(UUID id) {
        carStorage.remove(id);
    }

    @Override
    public Optional<Car> findById(UUID id) {
        return Optional.ofNullable(carStorage.get(id));
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(carStorage.values());
    }
}
