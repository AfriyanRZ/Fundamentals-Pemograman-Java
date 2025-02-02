// Aplikasi Manajemen Kontak
import java.util.ArrayList;
import java.util.Scanner;

// Contact class (Encapsulation)
class Contact {
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}

// ContactManager class (Core Logic)
class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(String name, String phone) {
        contacts.add(new Contact(name, phone));
        System.out.println("Contact added: " + name + " (" + phone + ")");
    }

    public void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return; 
        }
        System.out.println("Contact List:");
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            System.out.println((i + 1) + ". " + contact.getName() + " - " + contact.getPhone());
        }
    }

    public void searchContact(String name) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("Found: " + contact.getName() + " - " + contact.getPhone());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contact found with the name: " + name);
        }
    }
}

// Main Class
public class ContactManagementApp {
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner scanner = new Scanner(System.in);

        while (true) { 
            System.out.println("\n--- Contact Management System ---");
            System.out.println("1. Add Contact");
            System.out.println("2. List Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Exit");
            System.out.print("Enter Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newLine

            switch (choice) {
                case 1:
                    System.out.print("Enter contact name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    manager.addContact(name, phone);
                    break;
                case 2:
                    manager.listContacts();
                    break;
                case 3: 
                    System.out.print("Enter name to search: ");
                    String SearchName = scanner.nextLine();
                    manager.searchContact(SearchName);
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
