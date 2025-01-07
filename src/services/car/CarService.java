package services.car;

import entities.vehicles.Car;

import java.util.List;

public interface CarService {
    void addCar(Car car);

    void listAllCars();

    List<Car> getCars();
}

