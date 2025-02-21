import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MahasiswaManager {

    private static List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "data_mahasiswa.txt";

    public static void main(String[] args) {
        // Membaca data dari file saat program dijalankan
        loadDataFromFile();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Menampilkan menu utama
            System.out.println("\n=== Menu Manajemen Data Mahasiswa ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Cari Mahasiswa");
            System.out.println("4. Tampilkan Statistik Mahasiswa");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
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
                    saveDataToFile();
                    System.out.println("Data telah disimpan. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Coba lagi.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        // Validasi format NIM
        while (!nim.matches("\\d{8}")) {
            System.out.print("Format NIM salah. Masukkan NIM (8 digit angka): ");
            nim = scanner.nextLine();
        }

        System.out.print("Masukkan Nama: ");
        String name = scanner.nextLine();
        // Validasi nama hanya huruf
        while (!name.matches("[a-zA-Z\\s]+")) {
            System.out.print("Nama hanya boleh berisi huruf. Masukkan Nama: ");
            name = scanner.nextLine();
        }

        System.out.print("Masukkan Jurusan: ");
        String major = scanner.nextLine();
        // Validasi daftar jurusan
        List<String> validMajors = Arrays.asList("Informatika", "Sistem Informasi", "Teknik Elektro");
        while (!validMajors.contains(major)) {
            System.out.print("Jurusan tidak valid. Pilih salah satu (Informatika, Sistem Informasi, Teknik Elektro): ");
            major = scanner.nextLine();
        }

        students.add(new Student(nim, name, major));
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
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
        System.out.print("Masukkan kata kunci pencarian: ");
        String keyword = scanner.nextLine().toLowerCase();

        List<Student> results = students.stream()
                .filter(student -> student.getNim().toLowerCase().contains(keyword)
                        || student.getName().toLowerCase().contains(keyword)
                        || student.getMajor().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("Mahasiswa tidak ditemukan.");
        } else {
            System.out.println("\n=== Hasil Pencarian ===");
            System.out.printf("%-10s %-20s %-20s\n", "NIM", "Nama", "Jurusan");
            System.out.println("----------------------------------------------------------");
            for (Student student : results) {
                System.out.printf("%-10s %-20s %-20s\n", student.getNim(), student.getName(), student.getMajor());
            }
        }
    }

    private static void showStatistics() {
        if (students.isEmpty()) {
            System.out.println("Belum ada data mahasiswa untuk statistik.");
            return;
        }

        // Statistik jumlah mahasiswa per jurusan
        Map<String, Long> majorCounts = students.stream()
                .collect(Collectors.groupingBy(Student::getMajor, Collectors.counting()));

        System.out.println("\n=== Statistik Mahasiswa ===");
        System.out.println("Jumlah mahasiswa: " + students.size());

        System.out.println("\nJumlah mahasiswa per jurusan:");
        majorCounts.forEach((major, count) -> System.out.println(major + ": " + count));

        // Jurusan dengan mahasiswa terbanyak
        String topMajor = Collections.max(majorCounts.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Jurusan dengan jumlah mahasiswa terbanyak: " + topMajor);
    }

    private static void saveDataToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (Student student : students) {
                writer.write(student.getNim() + "," + student.getName() + "," + student.getMajor() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    private static void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File data tidak ditemukan. Memulai dengan data kosong.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }
}

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
}
