package repositories.car;

import entities.vehicles.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository {
    void save(Car car);

    void delete(UUID id);

    Optional<Car> findById(UUID id);

    List<Car> findAll();
}
