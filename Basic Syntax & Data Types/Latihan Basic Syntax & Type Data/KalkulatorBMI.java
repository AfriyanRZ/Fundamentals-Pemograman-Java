public class KalkulatorBMI {
    public static void main(String[] args) {
        //  Data Input
        String nama = "Yang megang Martapura";
        double beratBadan = 73;   // dalam kilogram(KG)
        double tinggiBadan = 1.82;  // dalam meter(M)
        int umur = 40;
        char jenisKelamin = 'L';    // L = laki-laki, P = perempuan
        boolean isAktif = true; // Status aktifitas fisik

        //  Hitung BMI
        double bmi = beratBadan / (tinggiBadan * tinggiBadan);

        //  Hitung kebutuhan kalori dasar
        double bmr;
        if (jenisKelamin == 'L') {
            bmr = 88.362 + (13.397 * beratBadan) + (4.799 * tinggiBadan * 100) - (5.677 * umur);
        } else {
            bmr = 447.593 + (9.247 * beratBadan) + (3.098 * tinggiBadan * 100) - (4.330 * umur);
        }

        //  Hitung kebutuhan kalori total berdasarkan aktivitas
        double totalKalori = isAktif ? bmr * 1.55 : bmr * 1.2;

        //  Tentukan kategori BMI dan rekomendasi
        String kategori;
        String rekomendasi;

        if (bmi < 18.5) {
            kategori = "Kurus";
            rekomendasi = "Tingkatkan asupan kalori dan protein";
        } else if (bmi < 25) {
            kategori = "Normal";
            rekomendasi = "Pertahankan pola makan sehat";
        } else if (bmi < 30) {
            kategori = "Gemuk";
            rekomendasi = "Kurangi karbohidrat, perbanyak olahraga";
        } else {
            kategori = "Obesitas";
            rekomendasi = "Konsultasi dengan dokter gizi";
        }

        //  Output hasil
        System.out.println("==== KALKULATOR KESEHATAN ====");
        System.out.println("Nama: " + nama);
        System.out.println("Usia: " + umur + " tahun");
        System.out.println("Jenis Kelamin: " + (jenisKelamin == 'L' ? "Laki-laki" : "perempuan"));
        System.out.println("Status Aktivitas: " + (isAktif ? "Aktif" : "Tidak Aktif"));
        System.out.println("\n==== HASIL ANALISIS ====");
        System.out.println("BMI: " + String.format("%.2f", bmi));
        System.out.println("Kategori: " + kategori);
        System.out.println("BMR: " + String.format("%.2f", bmr) + " kalori/hari");
        System.out.println("Kebutuhan Kalori: " + String.format("%.2f", totalKalori) + " kalori/hari");
        System.out.println("Rekomendasi: " + rekomendasi);
    }
}
