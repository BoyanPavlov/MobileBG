package entities.vehicles;

public class Car extends Vehicle {
    private boolean isManual;

    public Car(String brand, String model, int year, boolean isManual) {
        super(brand + " " + model, brand, model, year);
        this.isManual = isManual;
    }

    public boolean isManual() {
        return isManual;
    }

    public void setManual(boolean manual) {
        isManual = manual;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", isManual=" + isManual +
                '}';
    }
}
