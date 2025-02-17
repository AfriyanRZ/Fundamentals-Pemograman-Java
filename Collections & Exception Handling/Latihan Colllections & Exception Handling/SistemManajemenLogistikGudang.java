import java.util.*;

class Barang {
    private String nama;
    private int jumlah;
    private Date tanggalKadaluwarsa;
    private String kategori;
    private double harga;

    public Barang(String nama, int jumlah, Date tanggalKadaluwarsa, String kategori, double harga) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.tanggalKadaluwarsa = tanggalKadaluwarsa;
        this.kategori = kategori;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void kurangiJumlah(int jumlah) {
        this.jumlah -= jumlah;
    }

    public Date getTanggalKadaluwarsa() {
        return tanggalKadaluwarsa;
    }

    public String getKategori() {
        return kategori;
    }

    public double getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return "Barang: " + nama + " | Kategori: " + kategori + " | Harga: " + harga + " | Jumlah: " + jumlah + " | Kadaluwarsa: " + tanggalKadaluwarsa;
    }
}

class Gudang {
    private Map<String, Barang> stok;
    private PriorityQueue<String> pesanan;
    private LinkedList<String> pengiriman;
    private Map<String, List<String>> pelangganPesanan;
    private List<String> riwayatPengiriman;
    private Map<String, Double> diskon;

    public Gudang() {
        stok = new HashMap<>();
        pesanan = new PriorityQueue<>();
        pengiriman = new LinkedList<>();
        pelangganPesanan = new HashMap<>();
        riwayatPengiriman = new ArrayList<>();
        diskon = new HashMap<>();
    }

    public void tambahBarang(String nama, int jumlah, Date tanggalKadaluwarsa, String kategori, double harga) {
        stok.put(nama, new Barang(nama, jumlah, tanggalKadaluwarsa, kategori, harga));
        System.out.println("Barang ditambahkan: " + nama);
    }

    public void hapusBarangKadaluwarsa() {
        Date sekarang = new Date();
        stok.entrySet().removeIf(entry -> entry.getValue().getTanggalKadaluwarsa().before(sekarang));
        System.out.println("Barang kadaluwarsa telah dihapus dari stok.");
    }

    public void buatPesanan(String pelanggan, String namaBarang) {
        if (stok.containsKey(namaBarang) && stok.get(namaBarang).getJumlah() > 0) {
            pesanan.add(namaBarang);
            pelangganPesanan.putIfAbsent(pelanggan, new ArrayList<>());
            pelangganPesanan.get(pelanggan).add(namaBarang);
            System.out.println("Pesanan diterima: " + namaBarang + " untuk " + pelanggan);
        } else {
            System.out.println("Pesanan gagal, stok tidak mencukupi atau barang tidak tersedia!");
        }
    }

    public void prosesPesanan() {
        if (!pesanan.isEmpty()) {
            String barangDiproses = pesanan.poll();
            Barang barang = stok.get(barangDiproses);
            if (barang != null && barang.getJumlah() > 0) {
                barang.kurangiJumlah(1);
                pengiriman.add(barangDiproses);
                riwayatPengiriman.add(barangDiproses);
                System.out.println("Pesanan " + barangDiproses + " sedang dikirim!");
            } else {
                System.out.println("Pesanan gagal, stok habis!");
            }
        } else {
            System.out.println("Tidak ada pesanan yang diproses.");
        }
    }

    public void lihatStatusPesanan() {
        System.out.println("=== Status Pesanan ===");
        System.out.println("Dalam Antrian: " + pesanan);
        System.out.println("Sedang dikirim: " + pengiriman);
    }

    public void batalkanPesanan(String pelanggan, String namaBarang) {
        if (pelangganPesanan.containsKey(pelanggan) && pelangganPesanan.get(pelanggan).remove(namaBarang)) {
            pesanan.remove(namaBarang);
            System.out.println("Pesanan " + namaBarang + " dari " + pelanggan + " telah dibatalkan.");
        } else {
            System.out.println("Pesanan tidak ditemukan!");
        }
    }

    public void lihatStok() {
        System.out.println("=== Daftar Stok ===");
        for (Barang barang : stok.values()) {
            System.out.println(barang);
            if (barang.getJumlah() < 5) {
                System.out.println("⚠️ Stok " + barang.getNama() + " hampir habis!");
            }
        }
    }

    public void lihatRiwayatPengiriman() {
        System.out.println("=== Riwayat Pengiriman ===");
        for (String barang : riwayatPengiriman) {
            System.out.println(barang);
        }
    }

    public void lihatPelangganPesanan() {
        System.out.println("=== Daftar Pelanggan & Pesanannya ===");
        for (Map.Entry<String, List<String>> entry : pelangganPesanan.entrySet()) {
            System.out.println("Pelanggan: " + entry.getKey() + " | Pesanan: " + entry.getValue());
        }
    }
}

public class SistemManajemenLogistikGudang {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gudang gudang = new Gudang();

        while (true) { 
            System.out.println("\n=== SISTEM GUDANG LOGISTIK ===");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Hapus Barang Kadaluwarsa");
            System.out.println("3. Buat Pesanan");
            System.out.println("4. Proses Pesanan");
            System.out.println("5. Lihat Stok");
            System.out.println("6. Lihat Status Pesanan");
            System.out.println("7. Batalkan Pesanan");
            System.out.println("8. Lihat Riwayat Pengiriman");
            System.out.println("9. Lihat Pelanggan & Pesanannya");
            System.out.println("10. Keluar");
            System.out.println("Pilih Menu: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukan nama barang: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukan jumlah: ");
                    int jumlah = scanner.nextInt();
                    System.out.print("Masukan tahun kadaluwarsa: ");
                    int tahun = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Masukan kategori: ");
                    String kategori = scanner.nextLine();
                    System.out.print("Masukan Harga: ");
                    double harga = scanner.nextDouble();
                    Date tanggalKadaluwarsa = new GregorianCalendar(tahun, Calendar.JANUARY, 1).getTime();
                    gudang.tambahBarang(nama, jumlah, tanggalKadaluwarsa, kategori, harga);
                    break;
                case 2:
                    gudang.hapusBarangKadaluwarsa();
                    break;
                case 3:
                    System.out.print("Masukan nama pelanggan: ");
                    String pelangganPesanan = scanner.nextLine();
                    System.out.print("Masukan nama barang: ");
                    String namaBarangPesanan = scanner.nextLine();
                    gudang.buatPesanan(pelangganPesanan, namaBarangPesanan);
                    break;
                case 4:
                    gudang.prosesPesanan();
                    break;
                case 5: 
                    gudang.lihatStok();
                    break;
                case 6:
                    gudang.lihatStatusPesanan();
                    break;
                case 7:
                    System.out.print("Masukan nama pelanggan: ");
                    String pelanggan = scanner.nextLine();
                    System.out.print("Masukan nama barang yang dibatalkan: ");
                    String barangBatal = scanner.nextLine();
                    gudang.batalkanPesanan(pelanggan, barangBatal);
                    break;
                case 8:
                    gudang.lihatRiwayatPengiriman();
                    break;
                case 9:
                    gudang.lihatPelangganPesanan();
                    break;
                case 10:
                    System.out.println("Terima kasih telah menggunakan sistem!");
                    scanner.close();
                    return;
            }
        }
    }
}
