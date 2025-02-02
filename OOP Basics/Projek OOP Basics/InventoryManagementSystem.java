
import java.util.ArrayList;
import java.util.stream.Collectors;


class Item {
    String name;
    double price;
    int quantity;
    Category category;

    public Item(String name, double price, int quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
}

class Category {

    public Category(String name) {
    }
}

class Inventory{
    private final ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public ArrayList<Item> getItemByCategory(Category category) {
        return items.stream()
                     .filter(item -> item.category.equals(category))
                     .collect(Collectors.toCollection(ArrayList::new));
    }

    public void printReport() {
        System.out.println("Inventory Report:");
        for (Item item : items) {
            System.out.println(item.name + " - Price: $" + item.price + ", Quantity: " + item.quantity);
        }
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Category electronics = new Category("Electronics");
        Category clothing = new Category("Clothing");

        Inventory inventory = new Inventory();
        inventory.addItem(new Item("Laptop", 999.99, 10, electronics));
        inventory.addItem(new Item("T-Shirt", 24.99, 50, clothing));
        inventory.addItem(new Item("Headphones", 79.99, 20, electronics));

        ArrayList<Item> electronicItem = inventory.getItemByCategory(electronics);
        System.out.println("Electronics Items:");
        for (Item item : electronicItem) {
            System.out.println("- " + item.name);
        }

        inventory.removeItem(electronicItem.get(0));
        inventory.printReport();
    }
}