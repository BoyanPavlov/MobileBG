import entities.search.CaseInsensitiveFilter;
import entities.search.ExactValueFilter;
import entities.search.Filter;
import entities.search.RangeFilter;
import vehicle.Car;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla", 2021, true);
        Car car2 = new Car("Bmw", "e60", 2000, false);

        // всичките хиляди коли които имаме
        List<Car> cars = List.of(car, car2);

        List<Filter<Car>> filters = List.of(
                new ExactValueFilter<>(Car::getBrand, "Toyota"),
                new CaseInsensitiveFilter<>(Car::getModel, "Corolla"),
                new RangeFilter<>(Car::getYear, 2000, 2022),
                new RangeFilter<>(Car::getBrand, "Bmw", "Toyota")
        );

        // само колите от филтрите
        List<Car> matchingCars = filterCars(cars, filters);
        System.out.println("Matching cars:");
        matchingCars.forEach(System.out::println);
    }

    // не му е мястото тук, само за демонстративни цели е
    private static List<Car> filterCars(List<Car> cars, List<Filter<Car>> filters) {
        return cars.stream()
                .filter(car -> filters.stream().allMatch(filter -> filter.matches(car)))
                .toList();
    }
}