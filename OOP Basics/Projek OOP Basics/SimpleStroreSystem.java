// Sistem Manajemen Toko
import java.util.ArrayList;
import java.util.Scanner;

// Product class (Encapsulation)
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// StoreManager class
class StoreManager {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(String name, double price) {
        products.add(new Product(name, price));
        System.out.println("Product added: " + name);
    }

    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        System.out.println("Product List:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice());
        }
    }

    public void calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        System.out.println("Total price of all products: $" + total);
    }
}

// Main Class 
public class SimpleStroreSystem {
    public static void main(String[] args) {
        StoreManager store = new StoreManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Store Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Calculate Total Price");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newLine

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product pricce: ");
                    double price = scanner.nextDouble();
                    store.addProduct(name, price);
                    break;
                case 2: 
                    store.listProducts();
                    break;
                case 3:
                    store.calculateTotal();
                    break;
                case 4:
                    System.out.println("Exiting the System...");
                    scanner.close();
                    return;
                default: 
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
