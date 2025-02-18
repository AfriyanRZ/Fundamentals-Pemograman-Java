import java.util.*;

public class SistemPemesananTiketBioskop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<String> kursiDipesan = new HashSet<>();

        // Inisialisasi daftar kursi (misal: A1 hingga A10)
        String[] semuaKursi = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10"};

        System.out.println("=== Sistem Pemesanan Tiket Bioskop ===");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Pesan Kursi");
            System.out.println("2. Batalkan Pemesanan");
            System.out.println("3. Tampilkan Kursi yang Tersedia");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            try {
                int pilihan = Integer.parseInt(scanner.nextLine());

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan nomor kursi yang ingin dipesan (contoh: A1): ");
                        String kursiPesan = scanner.nextLine().toUpperCase();

                        if (kursiDipesan.contains(kursiPesan)) {
                            System.out.println("Kursi " + kursiPesan + " sudah dipesan. Silakan pilih kursi lain.");
                        } else if (!isKursiValid(semuaKursi, kursiPesan)) {
                            System.out.println("Nomor kursi tidak valid. Silakan masukkan kursi yang tersedia.");
                        } else {
                            kursiDipesan.add(kursiPesan);
                            System.out.println("Kursi " + kursiPesan + " berhasil dipesan.");
                        }
                        break;

                    case 2:
                        System.out.print("Masukkan nomor kursi yang ingin dibatalkan (contoh: A1): ");
                        String kursiBatal = scanner.nextLine().toUpperCase();

                        if (kursiDipesan.contains(kursiBatal)) {
                            kursiDipesan.remove(kursiBatal);
                            System.out.println("Kursi " + kursiBatal + " berhasil dibatalkan.");
                        } else {
                            System.out.println("Kursi " + kursiBatal + " belum dipesan atau tidak valid.");
                        }
                        break;

                    case 3:
                        System.out.println("\nKursi yang tersedia:");
                        for (String kursi : semuaKursi) {
                            if (!kursiDipesan.contains(kursi)) {
                                System.out.print(kursi + " ");
                            }
                        }
                        System.out.println();
                        break;

                    case 4:
                        System.out.println("Terima kasih telah menggunakan sistem pemesanan tiket bioskop!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih menu yang tersedia.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Harap masukkan angka untuk memilih menu.");
            }
        }
    }

    // Metode untuk memvalidasi apakah kursi yang dimasukkan valid
    private static boolean isKursiValid(String[] semuaKursi, String kursi) {
        for (String k : semuaKursi) {
            if (k.equalsIgnoreCase(kursi)) {
                return true;
            }
        }
        return false;
    }
}
