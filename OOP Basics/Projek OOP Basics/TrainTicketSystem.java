// Sistem Pemesanan Tiket Kereta
import java.util.ArrayList;
import java.util.Scanner;

// Passenger class (Encapsulation)
class Passenger {
    private String name;
    private String destination;

    public Passenger(String name, String destination) {
        this.name = name;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }
}

// TicketBooking class (Core Logic)
class TicketBooking {
    private ArrayList<Passenger> passengers = new ArrayList<>();

    public void bookTicket(String name, String destination) {
        Passenger passenger = new Passenger(name, destination);
        passengers.add(passenger);
        System.out.println("Ticket booked for: " + name + " to " + destination);
    }

    public void listPassengers() {
        if (passengers.isEmpty()) {
            System.out.println("No passengers have booked tickets yet.");
            return;
        }
        System.out.println("Passenger List:");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger passenger = passengers.get(i);
            System.out.println((i + 1) + ". " + passenger.getName() + " -> " + passenger.getDestination());
        }
    }

    public void cancelTicket(int index) {
        if (index < 0 || index >= passengers.size()) {
            System.out.println("Invalid passenger index!");
            return;
        }
        Passenger passenger = passengers.remove(index);
        System.out.println("Ticket canceled for: " + passenger.getName());
    }
}

// Main class
public class TrainTicketSystem {
    public static void main(String[] args) {
        TicketBooking bookingSystem = new TicketBooking();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Train Ticket Booking System ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. List Passengers");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter passenger name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine();
                    bookingSystem.bookTicket(name, destination);
                    break;
                case 2:
                    bookingSystem.listPassengers();
                    break;
                case 3:
                    System.out.print("Enter passenger number to cancel: ");
                    int index = scanner.nextInt() - 1;
                    bookingSystem.cancelTicket(index);
                    break;
                case 4:
                    System.out.println("Exiting the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
