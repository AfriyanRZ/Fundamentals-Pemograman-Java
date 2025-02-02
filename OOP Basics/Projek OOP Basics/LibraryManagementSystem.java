// Main class for the Library System
import java.util.ArrayList;
import java.util.Scanner;

// Book class (Encapsulation)
class Book {
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.isBorrowed = false;
    }
}

// Library class (Core Logic)
class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void listBooks() {
        System.out.println("List of Books:");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor() +
                    (book.isBorrowed() ? " (Borrowed)" : ""));
        }
    }

    public void borrowBook(int bookIndex) {
        if (bookIndex < 0 || bookIndex >= books.size()) {
            System.out.println("Invalid book index!");
            return;
        }
        Book book = books.get(bookIndex);
        if (book.isBorrowed()) {
            System.out.println("Book is already borrowed!");
        } else {
            book.borrowBook();
            System.out.println("You borrowed: " + book.getTitle());
        }
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(title, author));
                    break;
                case 2:
                    library.listBooks();
                    break;
                case 3:
                    System.out.print("Enter book number to borrow: ");
                    int bookIndex = scanner.nextInt() - 1;
                    library.borrowBook(bookIndex);
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
