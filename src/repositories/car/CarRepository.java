package repositories.car;

import entities.vehicles.Car;

import java.util.List;

public interface CarRepository {
    void save(Car car);

    List<Car> findAll();
}
