class Car {
    String make, model;
    int year;
    double speed;

    void accelerate(double amount) {
        speed += amount;
    }

    void brake (double amount) {
        speed -= amount;
        if (speed < 0) {
            speed = 0;
        }
    }

    void displayInfo() {
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("speed " + " mph");
    }
}

public class CarDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.make = "Toyota";
        car.model = "Camry";
        car.year = 2020;
        car.accelerate(50.0);
        car.brake(20.0);
        car.displayInfo();
    }
}
