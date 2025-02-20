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
        return "Nim: " + nim + ", Nama: " + nama + ", Jurusan: " + jurusan;
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
    private static HashSet<Mahasiswa> mahasiswaSet = new HashSet<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do { 
            System.out.println("\n=== Sistem Manajemen Data Mahasiswa ===");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Hapus Mahasiswa");
            System.out.println("3. Cari Mahasiswa");
            System.out.println("4. Tampilkan Semua Mahasiswa");
            System.out.println("5. Keluar");
            System.out.println("Pilih Menu: ");
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
                    System.out.println("Keluar dari program...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    // Menambahkan data mahasiswa
    private static void tambahMahasiswa() {
        try {
            System.out.print("Masukan NIM: ");
            String nim = scanner.nextLine();
            System.out.print("Masukan Nama: ");
            String nama = scanner.nextLine();
            System.out.print("Masukan Jurusan: ");
            String jurusan = scanner.nextLine();

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

    // Menghapus data Mahasiswa
    private static void hapusMahasiswa() {
        try {
            System.out.print("Masukan NIM mahasiswa yang ingin dihapus: ");
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
            System.out.print("Masukan NIM mahasiswa yang ingin dicari: ");
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
}
