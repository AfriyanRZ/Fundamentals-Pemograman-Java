import java.util.*;

public class SistemPemesananTiketBioskop {

    private static final HashMap<String, String> akunPengguna = new HashMap<>(); // Username dan Password
    private static final HashMap<String, HashSet<String>> jadwalFilm = new HashMap<>(); // Film dan kursi
    private static final HashMap<String, Integer> hargaFilm = new HashMap<>(); // Film dan harga
    private static final HashMap<String, HashMap<String, HashSet<String>>> sesiFilm = new HashMap<>(); // Film dan sesi serta kursi
    private static final String[] semuaSesi = {"Pagi", "Siang", "Sore", "Malam"};
    private static final String[] semuaKursi = {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inisialisasi akun pengguna
        akunPengguna.put("admin", "admin123");
        akunPengguna.put("user", "user123");

        // Inisialisasi data film, harga, dan sesi
        inisialisasiFilm();

        System.out.println("=== Sistem Pemesanan Tiket Bioskop ===");

        // Sistem Login
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        if (!akunPengguna.containsKey(username) || !akunPengguna.get(username).equals(password)) {
            System.out.println("Login gagal! Username atau password salah.");
            return;
        }

        boolean isAdmin = username.equals("admin");
        System.out.println("Login berhasil sebagai " + (isAdmin ? "Admin" : "Pengguna"));

        while (true) {
            System.out.println("\nMenu:");
            if (isAdmin) {
                System.out.println("1. Tambah Film");
                System.out.println("2. Lihat Film dan Harga");
            } else {
                System.out.println("1. Pilih Film dan Pesan Kursi");
                System.out.println("2. Batalkan Pemesanan");
                System.out.println("3. Tampilkan Kursi yang Tersedia");
            }
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            try {
                int pilihan = Integer.parseInt(scanner.nextLine());

                if (isAdmin) {
                    switch (pilihan) {
                        case 1:
                            tambahFilm(scanner);
                            break;
                        case 2:
                            lihatFilm();
                            break;
                        case 4:
                            System.out.println("Terima kasih telah menggunakan sistem sebagai Admin!");
                            return;
                        default:
                            System.out.println("Pilihan tidak valid. Silakan pilih menu yang tersedia.");
                    }
                } else {
                    switch (pilihan) {
                        case 1:
                            pesanKursi(scanner);
                            break;
                        case 2:
                            batalkanPesanan(scanner);
                            break;
                        case 3:
                            tampilkanKursiTersedia(scanner);
                            break;
                        case 4:
                            System.out.println("Terima kasih telah menggunakan sistem sebagai Pengguna!");
                            return;
                        default:
                            System.out.println("Pilihan tidak valid. Silakan pilih menu yang tersedia.");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Harap masukkan angka untuk memilih menu.");
            }
        }
    }

    private static void inisialisasiFilm() {
        String[] filmAwal = {"Avatar 2", "Spider-Man", "The Batman"};
        int[] hargaAwal = {50000, 45000, 55000};

        for (int i = 0; i < filmAwal.length; i++) {
            jadwalFilm.put(filmAwal[i], new HashSet<>());
            hargaFilm.put(filmAwal[i], hargaAwal[i]);
            sesiFilm.put(filmAwal[i], new HashMap<>());

            for (String sesi : semuaSesi) {
                sesiFilm.get(filmAwal[i]).put(sesi, new HashSet<>());
            }
        }
    }

    private static void tambahFilm(Scanner scanner) {
        System.out.print("Masukkan nama film baru: ");
        String filmBaru = scanner.nextLine();
        System.out.print("Masukkan harga tiket untuk film ini: ");

        try {
            int harga = Integer.parseInt(scanner.nextLine());
            jadwalFilm.put(filmBaru, new HashSet<>());
            hargaFilm.put(filmBaru, harga);
            sesiFilm.put(filmBaru, new HashMap<>());

            for (String sesi : semuaSesi) {
                sesiFilm.get(filmBaru).put(sesi, new HashSet<>());
            }

            System.out.println("Film " + filmBaru + " berhasil ditambahkan dengan harga Rp" + harga);
        } catch (NumberFormatException e) {
            System.out.println("Harga tiket tidak valid. Harap masukkan angka.");
        }
    }

    private static void lihatFilm() {
        System.out.println("\nDaftar Film dan Harga Tiket:");
        for (Map.Entry<String, Integer> entry : hargaFilm.entrySet()) {
            System.out.println("- " + entry.getKey() + ": Rp" + entry.getValue());
        }
    }

    private static void pesanKursi(Scanner scanner) {
        System.out.println("\nPilih Film:");
        for (String film : jadwalFilm.keySet()) {
            System.out.println("- " + film);
        }
        System.out.print("Masukkan nama film: ");
        String filmPilihan = scanner.nextLine();

        if (!jadwalFilm.containsKey(filmPilihan)) {
            System.out.println("Film tidak tersedia. Silakan pilih film yang ada.");
            return;
        }

        System.out.println("\nPilih Sesi:");
        for (String sesi : semuaSesi) {
            System.out.println("- " + sesi);
        }
        System.out.print("Masukkan sesi: ");
        String sesiPilihan = scanner.nextLine();

        if (!sesiFilm.get(filmPilihan).containsKey(sesiPilihan)) {
            System.out.println("Sesi tidak valid. Silakan pilih sesi yang tersedia.");
            return;
        }

        System.out.print("Masukkan nomor kursi yang ingin dipesan (contoh: A1): ");
        String kursiPesan = scanner.nextLine().toUpperCase();

        if (sesiFilm.get(filmPilihan).get(sesiPilihan).contains(kursiPesan)) {
            System.out.println("Kursi " + kursiPesan + " untuk film " + filmPilihan + " sesi " + sesiPilihan + " sudah dipesan. Silakan pilih kursi lain.");
        } else if (!isKursiValid(kursiPesan)) {
            System.out.println("Nomor kursi tidak valid. Silakan masukkan kursi yang tersedia.");
        } else {
            sesiFilm.get(filmPilihan).get(sesiPilihan).add(kursiPesan);
            System.out.println("Kursi " + kursiPesan + " untuk film " + filmPilihan + " sesi " + sesiPilihan + " berhasil dipesan.");
        }
    }

    private static void batalkanPesanan(Scanner scanner) {
        System.out.print("Masukkan nama film: ");
        String filmBatal = scanner.nextLine();

        if (!jadwalFilm.containsKey(filmBatal)) {
            System.out.println("Film tidak tersedia. Silakan pilih film yang ada.");
            return;
        }

        System.out.print("Masukkan sesi: ");
        String sesiBatal = scanner.nextLine();

        if (!sesiFilm.get(filmBatal).containsKey(sesiBatal)) {
            System.out.println("Sesi tidak valid. Silakan pilih sesi yang tersedia.");
            return;
        }

        System.out.print("Masukkan nomor kursi yang ingin dibatalkan (contoh: A1): ");
        String kursiBatal = scanner.nextLine().toUpperCase();

        if (sesiFilm.get(filmBatal).get(sesiBatal).contains(kursiBatal)) {
            sesiFilm.get(filmBatal).get(sesiBatal).remove(kursiBatal);
            System.out.println("Kursi " + kursiBatal + " untuk film " + filmBatal + " sesi " + sesiBatal + " berhasil dibatalkan.");
        } else {
            System.out.println("Kursi " + kursiBatal + " belum dipesan atau tidak valid.");
        }
    }

    private static void tampilkanKursiTersedia(Scanner scanner) {
        System.out.print("Masukkan nama film: ");
        String filmTersedia = scanner.nextLine();
    
        if (!jadwalFilm.containsKey(filmTersedia)) {
            System.out.println("Film tidak tersedia. Silakan pilih film yang ada.");
            return;
        }
    
        System.out.print("Masukkan sesi: ");
        String sesiTersedia = scanner.nextLine();
    
        if (!sesiFilm.get(filmTersedia).containsKey(sesiTersedia)) {
            System.out.println("Sesi tidak valid. Silakan pilih sesi yang tersedia.");
            return;
        }
    
        System.out.println("\nKursi yang tersedia untuk film " + filmTersedia + " sesi " + sesiTersedia + ":");
        for (String kursi : semuaKursi) {
            if (!sesiFilm.get(filmTersedia).get(sesiTersedia).contains(kursi)) {
                System.out.print(kursi + " ");
            }
        }
        System.out.println();
    }
    
    private static boolean isKursiValid(String kursi) {
        for (String validKursi : semuaKursi) {
            if (validKursi.equals(kursi)) {
                return true;
            }
        }
        return false;
    }
}
