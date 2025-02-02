// The Animal class represents the base class
class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void makeSound() {
        System.out.println("The animal makes a sound.");
    }
}

// The Dog class inherits from Animal
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("The dog barks.");
    }
}

// The Cat class inherits from Animal
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("The cat meows.");
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        // Create instance of different animal types 
        Animal animal = new Animal("Generic Animal");
        Dog dog = new Dog("Buddy");
        Cat cat = new Cat("Whiskers");

        // Polymorphism in action
        animal.makeSound(); // Output: The animal makes a sound
        dog.makeSound(); // Output: The dog barks 
        cat.makeSound(); // Output: The cat meows

        // Store animals in an array and call their makeSound() method
        Animal[] animals = {animal, dog, cat};
        for (Animal a : animals) {
            a.makeSound();
        }
    }
}
