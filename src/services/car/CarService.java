package services.car;

import entities.vehicles.Car;

import java.util.UUID;

public interface CarService {
    void addCar(Car car);

    void deleteCar(UUID id);

    void findCarById(UUID id);

    void listAllCars();
}

