
public class AnalisisKeuangan {
    public static void main(String[] args) {
        //  Data keuangan pribadi
        String namaPengguna = "Afriyan Rakha Zaputra";
        double pendapatanPokok = 8000000.0;
        double pendapatanSampingan = 2000000.0;
        double tabunganAwal = 15000000.0;

        //  Pengeluaran
        double pengeluaranPokok = 3000000.0;
        double pengeluaranHiburan = 1000000.0;
        double cicilanKredit = 2000000.0;
        boolean punyaAsuransi = true;
        double biayaAsuransi = 500000.0;

        //  Perhitungan
        double totalPendapatan = pendapatanPokok + pendapatanSampingan;
        double totalPengeluaran = pengeluaranPokok + pengeluaranHiburan + 
            cicilanKredit + (punyaAsuransi ? biayaAsuransi : 0);
        double sisipendapatan = totalPendapatan - totalPengeluaran;

        //  Analisis keuangan
        double persenPengeluaran = (totalPengeluaran / totalPendapatan) * 100;
        double targetTabungan = totalPendapatan * 0.3;  // Target 30% dari pendapatan
        boolean mencapaiTarget = sisipendapatan >= targetTabungan;

        //  Proyeksi tabungan
        double proyeksiTabungan3Bulan = tabunganAwal + (sisipendapatan * 3);
        double proyeksiTabungan1Tahun = tabunganAwal + (sisipendapatan * 12);

        //  Kategori kesehatan keuangan
        char kategoriKeuangan;
        if (persenPengeluaran < 50) {
            kategoriKeuangan = 'A';
        } else if (persenPengeluaran < 70) {
            kategoriKeuangan = 'B';
        } else if (persenPengeluaran < 90) {
            kategoriKeuangan = 'C';
        } else {
            kategoriKeuangan = 'D';
        }

        //  Output hasil analisis
        System.out.println("=== ANALISIS KEUANGAN PRIBADI ===");
        System.out.println("Nama: " + namaPengguna);
        System.out.println("\n--- PENDAPATAN ---");
        System.out.println("Pendapatan Pokok: Rp " + String.format("%,.2f", pendapatanPokok));
        System.out.println("Pendapatan Sampingan: Rp " + String.format("%,.2f", pendapatanSampingan));
        System.out.println("Total Pendapatan: Rp " + String.format("%,.2f", totalPendapatan));
        
        System.out.println("\n--- PENGELUARAN ---");
        System.out.println("Pengeluaran Pokok: Rp " + String.format("%,.2f", pengeluaranPokok));
        System.out.println("Pengeluaran Hiburan: Rp " + String.format("%,.2f", pengeluaranHiburan));
        System.out.println("Cicilan Kredit: Rp "+ String.format("%,.2f", cicilanKredit));
        if (punyaAsuransi) {
            System.out.println("Biaya Asuransi: Rp " + String.format("%,.2f", biayaAsuransi));
        }
        System.out.println("Total Pengeluaran: Rp " + String.format("%,.2f", totalPengeluaran));

        System.out.println("\n--- ANALISIS ---");
        System.out.println("Sisa Pendapatan: Rp " + String.format("%,.2f", sisipendapatan));
        System.out.println("Presentase Pengeluaran: Rp " + String.format("%,.2f", persenPengeluaran));
        System.out.println("Target Tabungan: Rp " + String.format("%,.2f", targetTabungan));
        System.out.println("Status Target: " + (mencapaiTarget ? "Tercapai" : "Belum Tercapai"));
        System.out.println("Kategori Kesehatan Keuangan: " + kategoriKeuangan);

        System.out.println("\n--- PROYEKSI TABUNGAN ---");
        System.out.println("Tabungan Saat Ini: Rp " + String.format("%,.2f", tabunganAwal));
        System.out.println("Proyeksi 3 bulan: Rp " + String.format("%,.2f", proyeksiTabungan3Bulan));
        System.out.println("Proyeksi 1 Tahun: Rp " + String.format("%,.2f", proyeksiTabungan1Tahun));
    }
}
