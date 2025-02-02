
import java.util.ArrayList;

// The Car class represents a rental car
class Car {
    String make;
    String model;
        private final double pricePerDay;
        private boolean available;
    
        public Car(String make, String model, double pricePerDay) {
            this.make = make;
            this.model = model;
            this.pricePerDay = pricePerDay;
            this.available = true;
        }
    
        public void rentCar() {
            this.available = false;
        }
    
        public void returnCar() {
            this.available = true;
        }
    
        public boolean isAvailable() {
            return this.available;
        }
    
        public double getPricePerDay() {
            return this.pricePerDay;
        }
    }
    
    // The CarRentalSystem class manages the rental inventory
    class CarRentalSystem {
        private final ArrayList<Car> cars;
    
        public CarRentalSystem() {
            this.cars = new ArrayList<>();
        }
    
        public void addCar(String make, String model, double pricePerDay) {
            Car newCar = new Car(make, model, pricePerDay);
            this.cars.add(newCar);
        }
    
        public Car findAvailableCar(String make, String model) {
            for (Car car : this.cars) {
                if (car.isAvailable() && car.make.equals(make) && car.model.equals(model)) {
                return car;
            }
        }
        return null;
    }

    public void rentCar(Car car) {
        car.rentCar();
    }

    public void returnCar(Car car) {
        car.returnCar();
    }
}

// The main program
public class CarRentalDemo {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        // Add some cars to the rental system
        rentalSystem.addCar("Toyota", "Corolla", 50.0);
        rentalSystem.addCar("Honda", "Civic", 55.0);
        rentalSystem.addCar("Ford", "Mustang", 75.0);

        // Rent a car
        Car availableCar = rentalSystem.findAvailableCar("Toyota", "Corolla");
        if (availableCar != null) {
            rentalSystem.rentCar(availableCar);
            System.out.println("Rented a " + availableCar.make + " " + availableCar.model + " for $" + availableCar.getPricePerDay() + " per day.");
        } else {
            System.out.println("No available cars matching the requested model.");
        }

        // Return a car
        rentalSystem.returnCar(availableCar);
        System.out.println("Car returned.");
    }
}