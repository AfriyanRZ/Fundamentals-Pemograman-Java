
import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() throws Exception {
        if (!isAvailable) {
            throw new Exception("Buku sudah dipinjam!");
        }
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Judul: " + title + ", Penulis: " + author + ", Status: " + (isAvailable ? "Tersedia" : "Dipinjam");
    }
}

class Library {
    private List<Book> books;
    private Map<String, Book> borrowedBooks;

    public Library() {
        books = new ArrayList<>();
        borrowedBooks = new HashMap<>();
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Buku berhasil ditambahkan!");
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("Tidak ada buku dalam perpustakaan.");
            return;
        }
        System.out.println("\nDaftar Buku:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void borrowBook(String title, String borrower) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                try {
                    book.borrowBook();
                    borrowedBooks.put(borrower, book);
                    System.out.println("Buku berhasil dipinjam oleh " + borrower);
                } catch (Exception e) {
                    System.out.println("Gagal meminjam: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Buku tidak ditemukan!");
    }

    public void returnBook(String borrower) {
        if (!borrowedBooks.containsKey(borrower)) {
            System.out.println("Peminjam tidak memiliki buku untuk dikembalikan!");
            return;
        }

        Book book = borrowedBooks.get(borrower);
        book.returnBook();
        borrowedBooks.remove(borrower);
        System.out.println("Buku berhasil dikembalikan oleh " + borrower);
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) { 
            System.out.println("\n=== Sistem Manajemen Perpustakaan ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Lihat Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Keluar");
            System.out.println("Pilih Menu: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid! Harap masukan angka.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Masukan judul buku: ");
                    String title = scanner.nextLine();
                    System.out.print("Masukan nama penulis: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    library.displayBooks();
                    break;
                case 3:
                    System.out.print("Masukan judul buku yang ingin dipinjam: ");
                    String borrowTitle = scanner.nextLine();
                    System.out.print("Masukan nama peminjam: ");
                    String borrower = scanner.nextLine();
                    library.borrowBook(borrowTitle, borrower);
                    break;
                case 4:
                    System.out.print("Masukan nama peminjam yang ingin mengembalikan buku: ");
                    String returner = scanner.nextLine();
                    library.returnBook(returner);
                    break;
                case 5: 
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan!");
                    scanner.close();
                    return;
                default: 
                    System.out.println("Pilihan tidak valid!");  
            }
        }
    }
}