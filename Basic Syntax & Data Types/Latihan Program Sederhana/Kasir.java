public class Kasir {
    public static void main(String[] args) {
        // Data Barang
        String namaBarang = "Camel 12";
        int jumlahBeli = 3;
        double hargaSatuan = 20000.0;
        boolean dapatDiskon = true;

        // Perhitungan
        double totalHarga = hargaSatuan * jumlahBeli;
        double diskon = 0;

        // Kalo dapat diskon, hitung diskonnya
        if (dapatDiskon) {
            diskon = totalHarga * 0.1; // Diskon nya 10%
        }

        double hargaAkhir = totalHarga - diskon;

        // Tampilkan struk
        System.out.println("=== Struk Belanja ===");
        System.out.println("Barang: " + namaBarang);
        System.out.println("Jumlah: " + jumlahBeli);
        System.out.println("Harga Satuan: " + hargaSatuan);
        System.out.println("Total Harga " + totalHarga);
        System.out.println("Diskon: Rp " + diskon);
        System.out.println("Harga Akhir: Rp " + hargaAkhir);

        // Status Pembayaran 
        System.out.println("\nStatus Diskon: " + dapatDiskon);
    }
}
