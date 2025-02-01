public class NilaiSiswa {
    public static void main(String[] args) {
        // Data Siswa
        String namaMahasiswa = "Rusdi yang Terluka";
        int nomorInduk = 20123057;

        // Daftar Nilai
        double nilaiMatematika = 85.5;
        double nilaiBahasa = 90.0;
        double nilaiPancasila = 95.5;

        // Nilai Rata-Rata
        double rataRata = (nilaiMatematika + nilaiBahasa + nilaiPancasila) / 3;

        // Status Lulus nya minimal 75.0
        boolean lulus = rataRata >= 75.0;

        // Tentukan grade
        char grade;
        if (rataRata >= 90) {
            grade = 'A';
        } else if (rataRata >= 80) {
            grade = 'B';
        } else if (rataRata >=70) {
            grade = 'C';
        } else {
            grade = 'D';
        }

        // Hasil nya
        System.out.println("=== NILAI SISWA ===");
        System.out.println("Nama: " + namaMahasiswa);
        System.out.println("Nomor Induk: " + nomorInduk);
        System.out.println("\nNilai-nilai: ");
        System.out.println("Matematika: " + nilaiMatematika);
        System.out.println("Bahasa: " + nilaiBahasa);
        System.out.println("Pancasila: " + nilaiPancasila);
        System.out.println("\nRata-Rata");
        System.out.println("Grade: " + grade);
        System.out.println("Status Kelulusan: " + (lulus ? "Lulus" : "Tidak Lulus"));
    }
}
