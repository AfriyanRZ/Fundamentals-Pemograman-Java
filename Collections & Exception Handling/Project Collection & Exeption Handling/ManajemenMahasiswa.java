import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String nim;
    private String name;
    private String major;

    public Student(String nim, String name, String major) {
        this.nim = nim;
        this.name = name;
        this.major = major;
    }

    public String getNim() {
        return nim;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

public class ManajemenMahasiswa {

    private static List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "data_mahasiswa.txt";

    public static void main(String[] args) {
        loadDataFromFile();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Menu Manajemen Data Mahasiswa ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Cari Mahasiswa");
            System.out.println("4. Tampilkan Statistik Mahasiswa");
            System.out.println("5. Sorting dan Filtering Mahasiswa");
            System.out.println("6. Hapus Mahasiswa");
            System.out.println("7. Update Data Mahasiswa");
            System.out.println("8. Keluar");
            System.out.print("Pilih menu: ");
            
            while (!scanner.hasNextInt()) {
                System.out.println("Harap masukkan angka.");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudent(scanner);
                    break;
                case 4:
                    showStatistics();
                    break;
                case 5:
                    sortingAndFiltering(scanner);
                    break;
                case 6:
                    deleteStudent(scanner);
                    break;
                case 7:
                    updateStudent(scanner);
                    break;
                case 8:
                    saveDataToFile();
                    System.out.println("Data telah disimpan. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (choice != 8);

        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan Jurusan: ");
        String major = scanner.nextLine();
        students.add(new Student(nim, name, major));
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Tidak ada data mahasiswa.");
            return;
        }
        System.out.println("\n=== Data Mahasiswa ===");
        System.out.printf("%-10s %-20s %-20s\n", "NIM", "Nama", "Jurusan");
        System.out.println("----------------------------------------------------------");
        for (Student student : students) {
            System.out.printf("%-10s %-20s %-20s\n", student.getNim(), student.getName(), student.getMajor());
        }
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Masukkan NIM yang dicari: ");
        String nim = scanner.nextLine();
        for (Student student : students) {
            if (student.getNim().equals(nim)) {
                System.out.println("Mahasiswa ditemukan: " + student.getName() + " - " + student.getMajor());
                return;
            }
        }
        System.out.println("Mahasiswa tidak ditemukan.");
    }

    private static void showStatistics() {
        Map<String, Long> stats = students.stream()
                .collect(Collectors.groupingBy(Student::getMajor, Collectors.counting()));
        System.out.println("\n=== Statistik Mahasiswa ===");
        stats.forEach((major, count) -> System.out.println(major + ": " + count + " mahasiswa"));
    }

    private static void sortingAndFiltering(Scanner scanner) {
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Mahasiswa setelah sorting berdasarkan nama:");
        displayStudents();
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Masukkan NIM mahasiswa yang ingin dihapus: ");
        String nim = scanner.nextLine();
        students.removeIf(student -> student.getNim().equals(nim));
        System.out.println("Mahasiswa dengan NIM " + nim + " telah dihapus.");
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Masukkan NIM mahasiswa yang ingin diperbarui: ");
        String nim = scanner.nextLine();
        for (Student student : students) {
            if (student.getNim().equals(nim)) {
                System.out.print("Masukkan Nama baru: ");
                student.setName(scanner.nextLine());
                System.out.print("Masukkan Jurusan baru: ");
                student.setMajor(scanner.nextLine());
                System.out.println("Data mahasiswa berhasil diperbarui.");
                return;
            }
        }
        System.out.println("Mahasiswa tidak ditemukan.");
    }

    private static void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    students.add(new Student(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file data: " + e.getMessage());
        }
    }

    private static void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.getNim() + "," + student.getName() + "," + student.getMajor());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan file data: " + e.getMessage());
        }
    }
}
