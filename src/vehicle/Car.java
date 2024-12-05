package vehicle;

import java.util.Objects;

public class Car {
    private String brand;
    private String model;
    private Integer year;
    private boolean isManual;

    // Constructor
    public Car(String brand, String model, Integer year, boolean isManual) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.isManual = isManual;
    }

    // Getters
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public boolean isManual() {
        return isManual;
    }

    // Setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setManual(boolean isManual) {
        this.isManual = isManual;
    }

    // toString Method
    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", isManual=" + isManual +
                '}';
    }

    // Equals and HashCode (optional)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isManual == car.isManual &&
                brand.equals(car.brand) &&
                model.equals(car.model) &&
                year.equals(car.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, isManual);
    }
}
