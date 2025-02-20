import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

// Kelas Mahasiswa
class Mahasiswa {
    private String nim;
    private String nama;
    private String jurusan;

    // Constructor
    public Mahasiswa(String nim, String nama, String jurusan) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
    }

    // Getter
    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    // Override toString untuk cetak data mahasiswa
    @Override
    public String toString() {
        return nim + "," + nama + "," + jurusan;
    }

    // Override equals dan hashCode untuk memastikan NIM unik
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mahasiswa mahasiswa = (Mahasiswa) obj;
        return nim.equals(mahasiswa.nim);
    }

    @Override
    public int hashCode() {
        return nim.hashCode();
    }
}

// Main Program
public class ManajemenMahasiswa {
    private static final String FILE_NAME = "data_mahasiswa.txt";
    private static HashSet<Mahasiswa> mahasiswaSet = new HashSet<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String[] VALID_JURUSAN = {"Informatika", "Sistem Informasi", "Teknik Komputer"};

    public static void main(String[] args) {
        loadDataFromFile();

        int pilihan;
        do {
            System.out.println("\n=== Sistem Manajemen Data Mahasiswa ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Cari Mahasiswa");
            System.out.println("4. Tampilkan Semua Mahasiswa");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    tambahMahasiswa();
                    break;
                case 2:
                    hapusMahasiswa();
                    break;
                case 3:
                    cariMahasiswa();
                    break;
                case 4:
                    tampilkanMahasiswa();
                    break;
                case 5:
                    saveDataToFile();
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    // Menambah data mahasiswa
    private static void tambahMahasiswa() {
        try {
            System.out.print("Masukkan NIM (harus 8 digit angka): ");
            String nim = scanner.nextLine();
            if (!nim.matches("\\d{8}")) {
                throw new IllegalArgumentException("NIM harus terdiri dari 8 digit angka!");
            }

            System.out.print("Masukkan Nama (hanya huruf): ");
            String nama = scanner.nextLine();
            if (!nama.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Nama hanya boleh berisi huruf dan spasi!");
            }

            System.out.print("Masukkan Jurusan (Informatika, Sistem Informasi, Teknik Komputer): ");
            String jurusan = scanner.nextLine();
            boolean jurusanValid = false;
            for (String j : VALID_JURUSAN) {
                if (jurusan.equalsIgnoreCase(j)) {
                    jurusanValid = true;
                    break;
                }
            }
            if (!jurusanValid) {
                throw new IllegalArgumentException("Jurusan tidak valid! Harus salah satu dari: Informatika, Sistem Informasi, Teknik Komputer.");
            }

            Mahasiswa mhs = new Mahasiswa(nim, nama, jurusan);
            if (mahasiswaSet.add(mhs)) {
                System.out.println("Mahasiswa berhasil ditambahkan!");
            } else {
                throw new Exception("Mahasiswa dengan NIM ini sudah ada!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Menghapus data mahasiswa
    private static void hapusMahasiswa() {
        try {
            System.out.print("Masukkan NIM mahasiswa yang ingin dihapus: ");
            String nim = scanner.nextLine();

            Mahasiswa mhsToRemove = null;
            for (Mahasiswa mhs : mahasiswaSet) {
                if (mhs.getNim().equals(nim)) {
                    mhsToRemove = mhs;
                    break;
                }
            }

            if (mhsToRemove != null) {
                mahasiswaSet.remove(mhsToRemove);
                System.out.println("Mahasiswa berhasil dihapus!");
            } else {
                throw new Exception("Mahasiswa dengan NIM ini tidak ditemukan!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Mencari data mahasiswa
    private static void cariMahasiswa() {
        try {
            System.out.print("Masukkan NIM mahasiswa yang ingin dicari: ");
            String nim = scanner.nextLine();

            for (Mahasiswa mhs : mahasiswaSet) {
                if (mhs.getNim().equals(nim)) {
                    System.out.println("Mahasiswa ditemukan: " + mhs);
                    return;
                }
            }
            throw new Exception("Mahasiswa dengan NIM ini tidak ditemukan!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Menampilkan semua data mahasiswa
    private static void tampilkanMahasiswa() {
        if (mahasiswaSet.isEmpty()) {
            System.out.println("Tidak ada data mahasiswa!");
        } else {
            System.out.println("\nDaftar Mahasiswa:");
            for (Mahasiswa mhs : mahasiswaSet) {
                System.out.println(mhs);
            }
        }
    }

    // Membaca data dari file
    private static void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    mahasiswaSet.add(new Mahasiswa(parts[0], parts[1], parts[2]));
                }
            }
            System.out.println("Data berhasil dimuat dari file.");
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan, mulai dengan data kosong.");
        } catch (IOException e) {
            System.out.println("Error saat membaca file: " + e.getMessage());
        }
    }

    // Menyimpan data ke file
    private static void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Mahasiswa mhs : mahasiswaSet) {
                writer.write(mhs.toString());
                writer.newLine();
            }
            System.out.println("Data berhasil disimpan ke file.");
        } catch (IOException e) {
            System.out.println("Error saat menyimpan file: " + e.getMessage());
        }
    }
}
