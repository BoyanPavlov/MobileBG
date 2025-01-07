package services.car;

import entities.vehicles.Car;
import repositories.car.CarRepository;

import java.util.List;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
        System.out.println("Car added: " + car);
    }

    @Override
    public void listAllCars() {
        System.out.println("All cars found: ");
        carRepository.findAll().forEach(System.out::println);
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }
}
