package repositories.car;

import entities.vehicles.Car;

import java.util.*;

public class CarRepositoryImpl implements CarRepository {
    private final List<Car> cars = new ArrayList<>();

    public CarRepositoryImpl() {
        // Pre-populate with initial data
        cars.add(new Car("Toyota", "Corolla", 2020, true));
        cars.add(new Car("Honda", "Civic", 2021, false));
        cars.add(new Car("BMW", "X5", 2019, true));
    }

    @Override
    public void save(Car car) {
        cars.add(car);
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(cars);
    }
}
