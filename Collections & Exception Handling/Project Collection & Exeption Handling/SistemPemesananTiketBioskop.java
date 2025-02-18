import java.util.*;

public class SistemPemesananTiketBioskop {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, HashSet<String>> jadwalFilm = new HashMap<>();

        // Inialisasi daftar kursi untuk setiap film
        String[] semuaKursi = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10"};
        jadwalFilm.put("Avatar 2", new HashSet<>());
        jadwalFilm.put("Spider-Man", new HashSet<>());
        jadwalFilm.put("The Batman", new HashSet<>());
        
        System.out.println("=== Sistem Pemesanan Tiket Bioskop ===");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Pilih Film dan Pesan Kursi");
            System.out.println("2. Batalkan Pemesanan");
            System.out.println("3. Tampilkan Kursi yang Tersedia");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            try {
                int pilihan = Integer.parseInt(scanner.nextLine());

                switch (pilihan) {
                    case 1:
                        System.out.println("\nPilih Film:");
                        for (String film : jadwalFilm.keySet()) {
                            System.out.println("- " + film);
                        }
                        System.out.print("Masukan nama film: ");
                        String filmPilihan = scanner.nextLine();

                        if (!jadwalFilm.containsKey(filmPilihan)) {
                            System.out.println("Film tidak tersedia. Silahkan pilih film yang ada.");
                            break;
                        }
                        System.out.print("Masukan nomor kursi yang ingin dipesan (contoh: A1): ");
                        String kursiPesan = scanner.nextLine().toUpperCase();

                        if (jadwalFilm.get(filmPilihan).contains(kursiPesan)) {
                            System.out.println("Kursi " + kursiPesan + " untuk film " + filmPilihan + " sudah dipesan. Silahkan pilih kursi yang lain.");
                        } else if (!isKursiValid(semuaKursi, kursiPesan)) {
                            System.out.println("Nomor kursi tidak valid. Silahkan masukan kursi yang tersedia.");
                        } else {
                            jadwalFilm.get(filmPilihan).add(kursiPesan);
                            System.out.println("Kursi " + kursiPesan + " untuk film " + filmPilihan + " berhasil dipesan.");
                        }
                        break;

                    case 2:
                        System.out.println("\nPilih Film:");
                        for (String film : jadwalFilm.keySet()) {
                            System.out.println("- " + film);
                        }
                        System.out.print("Masukan nama film: ");
                        String filmBatal = scanner.nextLine();

                        if (!jadwalFilm.containsKey(filmBatal)) {
                            System.out.println("Film tidak tersedia. Silahkan pilih film yang ada.");
                            break;
                        }

                        System.out.print("Masukan nomor kursi yang ingin dibatalkan (contoh: A1): ");
                        String kursiBatal = scanner.nextLine().toUpperCase();

                        if (jadwalFilm.get(filmBatal).contains(kursiBatal)) {
                            jadwalFilm.get(filmBatal).remove(kursiBatal);
                            System.out.println("Kursi " + kursiBatal + " untuk film " + filmBatal + " berhasil dibatalkan.");
                        } else {
                            System.out.println("Kursi " + kursiBatal + " belum dipesan atau tidak valid.");
                        }
                        break;

                    case 3:
                        System.out.println("\nPilih Film untuk melihat kursi yang tersedia:");
                        for (String film : jadwalFilm.keySet()) {
                            System.out.println("- " + film);
                        }
                        System.out.print("Masukan nama film: ");
                        String filmTersedia = scanner.nextLine();

                        if (!jadwalFilm.containsKey(filmTersedia)) {
                            System.out.println("Film tidak tersedia. Silahkan pilih film yang ada.");
                            break;
                        }

                        System.out.println("\nKursi yang tersedia untuk film " + filmTersedia + ":");
                        for (String kursi : semuaKursi) {
                            if (!jadwalFilm.get(filmTersedia).contains(kursi)) {
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
