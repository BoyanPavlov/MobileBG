package entities.vehicles;

import entities.products.Product;

import java.time.LocalDate;

public class Vehicle extends Product {
    protected String brand;
    protected String model;
    protected int year;

    public Vehicle(String name, String brand, String model, int year) {
        super(name, "Vehicle", LocalDate.now());
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return super.toString() + ", Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}

