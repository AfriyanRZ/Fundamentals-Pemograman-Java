class Circle {
    double radius;

    double getArea() {
        return Math.PI * radius * radius;
    }

    double getCircumference() {
        return 2 * Math.PI * radius;
    }
}

public class CircleDemo {
    public static void main(String[] args) {
        Circle c = new Circle();
        c.radius = 5.0;
        System.out.println("Area: " + c.getArea());
        System.out.println("Circumference: " + c.getCircumference());
    }
}
