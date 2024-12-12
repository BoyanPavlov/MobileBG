package services.car;

import entities.vehicles.Car;
import repositories.car.CarRepository;

import java.util.UUID;

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
    public void deleteCar(UUID id) {
        carRepository.findById(id).ifPresentOrElse(
                car -> {
                    carRepository.delete(id);
                    System.out.println("Car deleted: " + car);
                },
                () -> System.out.println("Car with ID " + id + " not found.")
        );
    }

    @Override
    public void findCarById(UUID id) {
        carRepository.findById(id).ifPresentOrElse(
                car -> System.out.println("Car found: " + car),
                () -> System.out.println("Car with ID " + id + " not found.")
        );
    }

    @Override
    public void listAllCars() {
        carRepository.findAll().forEach(System.out::println);
    }
}
