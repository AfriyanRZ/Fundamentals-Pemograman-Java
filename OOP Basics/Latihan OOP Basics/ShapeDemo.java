// The Shape interface defines common methods for shapes
interface Shape {
    double getArea();
    double getPerimeter();
}

// The Rectangle class implements the shape interface
class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return this.width * this.height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (this.width + this.height);
    }
}

// The Circle class implements the shape interface
class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }
}

// The abstract ShapeCalculator class
abstract class ShapeCalculator {
    public abstract void calculateShapeProperties(Shape shape);
}

// The concrete ShapeCalculatorImpl class
class ShapeCalculatorImpl extends ShapeCalculator {
    @Override
    public void calculateShapeProperties(Shape shape) {
        System.out.println("Area: " + shape.getArea());
        System.out.println("Perimeter: " + shape.getPerimeter());
    }
}

public class ShapeDemo {
    public static void main(String[] args) {
        // Create shapes and calculate their properties
        Shape rectangle = new Rectangle(5.0, 3.0);
        Shape circle = new Circle(4.0);

        ShapeCalculator calculator = new ShapeCalculatorImpl();
        calculator.calculateShapeProperties(rectangle);
        calculator.calculateShapeProperties(circle);
    }
}
